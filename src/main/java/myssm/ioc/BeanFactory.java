package myssm.ioc;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/20 20:01
 */
public interface BeanFactory {
    /**
     * 通过id获取bean的class
     * @author kayai
     * @date 2022/4/21 16:54
     * @param id: 要获取的id
     * @return java.lang.Object
     */
    public Object getBean(String id);
}
