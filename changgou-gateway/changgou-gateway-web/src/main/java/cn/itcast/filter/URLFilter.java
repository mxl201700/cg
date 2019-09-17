package cn.itcast.filter;

/**
 * Package: cn.itcast.filter
 * Author: Mxl
 * Date: Created in 2019/9/3 10:04
 **/
public class URLFilter {

    /**
     * 要放行的路径
     */
    private static final String nointerceterurl="/api/user/login,/api/user/add";

    /**
     * 用来判断 如果 当前的请求 在 放行的请求中存在,(不需要拦截 :true,否则需要拦截:false)
     * @return
     */
    public static boolean hasAuthorize(String uri){

        String[] split = nointerceterurl.split(",");
        for (String s : split) {

            if (s.equals(uri)){
                return true;
            }
        }
        //需要拦截
        return false;
    }
}
