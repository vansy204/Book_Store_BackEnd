package vn.bookstore.Book_Store_BackEnd.services;

public interface EmailService {
    public void sendEmail(String from, String to,String subject, String text);


}
