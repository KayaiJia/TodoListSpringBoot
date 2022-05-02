package myssm.spingmvc;

import myssm.ioc.BeanFactory;
import org.json.JSONObject;
import pojo.ReturnData;
import pojo.ReturnType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/20 20:12
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    private BeanFactory beanFactory;

    public DispatcherServlet(){

    }

    @Override
    public void init() throws ServletException {
        super.init();
        Object beanFactoryObj = getServletContext().getAttribute("beanFactory");
        if (beanFactoryObj != null){
            beanFactory = (BeanFactory) beanFactoryObj;
        }else {
            throw new RuntimeException("IOC容器创建失败");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath();
        servletPath = servletPath.substring(1);
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0,lastIndexOf);
        Object controllerBeanObj = beanFactory.getBean(servletPath);
        String operate = req.getParameter("operate");
        if (operate == null){
            operate = "index";
        }

        Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
        for (Method m : methods){
            String methodName = m.getName();
            if (operate.equals(methodName)){
                try {
                    Parameter[] parameters = m.getParameters();
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i=0;i<parameters.length;i++){
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        if ("req".equals(parameterName)){
                            parameterValues[i] = req;
                        }else if ("resp".equals(parameterName)){
                            parameterValues[i] = resp;
                        }else if ("session".equals(parameterName)){
                            parameterValues[i] = req.getSession();
                        } else if ("openid".equals(parameterName)) {
                            parameterValues[i] = req.getHeader("X-WX-OPENID");
                        } else {
                            String parameterValue = req.getParameter(parameterName);
                            String typeName = parameter.getType().getName();

                            Object parameterObj = parameterValue;

                            if (parameterObj != null) {
                                if ("java.lang.Integer".equals(typeName) && !"".equals(parameterValue)  && !parameterValue.isEmpty()) {
                                    parameterObj = Integer.parseInt(parameterValue);
                                } else if ("java.lang.Double".equals(typeName) && !"".equals(parameterValue)  && !parameterValue.isEmpty()) {
                                    parameterObj = Double.parseDouble(parameterValue);
                                }
                            }
                            parameterValues[i] = parameterObj;
                        }
                    }
                    m.setAccessible(true);
                    Object returnObj = m.invoke(controllerBeanObj,parameterValues);
                    ReturnData methodReturn = (ReturnData) returnObj;
                    if (methodReturn.getType().equals(ReturnType.valueOf("REDIRECT").name())){
                        String redirectStr = (String) methodReturn.getData("destination");
                        resp.sendRedirect(redirectStr);
                    }else{
                        Map<String, Object> data = methodReturn.getData();
                        JSONObject object = new JSONObject();
                        object.put("data",data);
                        resp.setCharacterEncoding("UTF-8");
                        resp.setHeader("Content-Type", "text/html;charset=utf-8");
                        resp.getWriter().print(object);
                    }
                    return;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new RuntimeException("operate 非法");
    }
}
