<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
    <head>
        <title>Dvd Store</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-2.1.0.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <style>
            table { 
               width : 30%;
               margin-top:5%;
            }
            th {
                text-align: center;
                font-weight: bold;
                font-size: 20px;
                color: blue;
                height: 40px;
                width: 200px;    
            }
            td {
                text-align:center;
                padding:10px 0px;
            }
            td input[type] {
                width: 250px;
            }
            label {
                width :200px;
                text-align:center;
            }
            .input {
                text-align:left;    
            }
            .empty {
                width:100px;
            }
            .text { 
                font-weight:bold;
            }
            input[type=date],input[type=number] {
                width:175px; 
            }
            .btn{
                text-align: left;
            }
            .title {
                text-align: center;
                margin-top : 10%;
                font-style:italic;
                font-weight:bold;
                color: blue;
            }
            body {
               background-image: url("blu-ray-disc-hd-dvd.jpg");
               background-repeat: no-repeat;
               background-position:  right bottom;
               background-attachment: fixed;
            }
            #newCustomer {
                text-align: center;
                width:400px;
            }
            ::-webkit-input-placeholder {
                text-align: center;
            }
        </style>
    </head>

    <body onload="loginResult()">
        <div class="title"><h1>Welcome to Dvd World!!!</h1></div>
            <table align="center" id="customer">
                <form>
                <tr>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="btn-group" data-toggle="buttons">
                            <label class="btn btn-default active">
                                <input type="radio" path="role" id="customer" name="user" value="customer"
                                    checked />Customer
                            </label>
                            <label class="btn btn-default">
                                <input type="radio" path="role" id="admin" name="user" value="admin"/> Admin 
                            </label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="text" path="userId" name="userId" pattern="[A-Za-z]{}" 
                            placeholder="User Name" required="required" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="password" path="password" name="password"  placeholder="Password"
                            required="required" />
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button type="submit" class="btn btn-success" name="login" formmethod="Post"
                            formaction="/SignIn" value="login" >login</button>
                    </td>
                </tr>
                </form>
                <tr>
                    <td colspan="2"> 
                    <form align="center">
                        <button id="newCustomer" class="btn" name="login" value="signUp"       
                            formaction="/CustomerRegister" formmethod="Post" value="addCustomer" >
                            New to DVDStore?&nbsp;&nbsp; <b>Sign Up<b> </button>
                    </form>
                    </td>
                </tr>
            </table>

    </body>

    <script>
        function loginResult() {
           var status = "${status}";
           var message = "${message}";
           if (status == 'fail' || status == 'success') {
              alert(message);
           }
        }
    </script>

</html>
