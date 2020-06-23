package Servlet;

import Dao.MessageDao;
import Model.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/")
public class GuestBookServlet extends HttpServlet {

    private MessageDao messageDao;

    @Override
    public void init() throws ServletException {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            messageDao = new MessageDao(getInitParameter("jdbc:mariadb://noelvaes.eu/StudentDB")
                    , getInitParameter("student")
                    , getInitParameter("student123"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Message> messages = messageDao.getAllMessages();

        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/WEB-INF/pages/guestbook.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Message message = new Message(LocalDateTime.now()
                , req.getParameter("name")
                ,req.getParameter("message"));
        messageDao.createMessage(message);
        resp.sendRedirect(req.getContextPath());

    }

    @Override
    public void destroy() {

        try {
            messageDao.getConnection().close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
