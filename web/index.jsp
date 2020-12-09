<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.dao.UserDao"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <link rel="stylesheet" href="assets/css/main.css">
        <jsp:include page="base.jsp"></jsp:include>
            <title>Login</title>
        </head>
        <body>

            <section class="login-block">
                <div class="container">

                    <div class="row">
                        <div class="col-md-4 login-sec">
                            <h2 class="text-center">Login </h2>
                            <form id="login-form" class="form-group" method="POST" autocomplete="off" action="index.jsp?op=login">

                                <div class="form-group" >
                                    <label for="InputUsername" class="text-uppercase">Username</label>
                                    <input type="text" name="username" class="form-control" placeholder="Enter Username" required>

                                </div>
                                <br/>
                                <div class="form-group">
                                    <label for="InputPassword1" class="text-uppercase">Password</label>
                                    <input type="password" name="password" class="form-control" placeholder="Enter Password" required>
                                </div>

                                <br/>
                                <div>
                                    <button type="submit" name="login" class="btn btn-login float-right">Login</button>
                                </div>

                            </form>

                        </div>
                        <div class="col-md-8 banner-sec"></div>
                    </div>
            </section>
        </body>
    <%
        if (request.getParameter("login") != null) {
            String op = request.getParameter("op");
            if (op != null && op.equals("login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                UserDao ud = new UserDao();
                if (ud.validate(username, password)) {
                    session.setAttribute("currentuser", username);
                    response.sendRedirect("urlentry.jsp");
                } else {
                    out.println("<script>alert('Incorrect Username & Password!!');</script>");
                }
            }
        }
    %>
</html>
