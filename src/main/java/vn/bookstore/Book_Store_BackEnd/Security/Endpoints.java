package vn.bookstore.Book_Store_BackEnd.Security;

public class Endpoints {
    public static final String front_end_host = "http://localhost:3000";
    public static final String[] PUBLIC_GET_ENDPOINTS = {
            "/book",
            "/book/**",
            "/picture",
            "/picture/**",
            "/user/search/exitstByUserName",
            "/user/search/exitstByEmail",
    };
    public static final String[] PUBLIC_POST_ENDPOINTS = {
        "/api/account/dang-ky"
    };
    public static final String[] ADMIN_GET_ENDPOINTS = {
            "/user",
            "/user/**"
    };
}
