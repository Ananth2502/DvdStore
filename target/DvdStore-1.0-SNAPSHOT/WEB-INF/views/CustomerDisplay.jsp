<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html>
    <head>
        <title>Dvd Store</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            form { 
              text-align : center;
            }
            div {
                overflow: auto;
                height : 500px;
            }
            .title {
                color:black;
            }
            table {
                width : 70%;
            }
            table,th {
                border : 2px solid black;
            }
            th {
                text-align: center;
                font-size: 20px;
                color: blue;
                height: 40px;
                width: auto;
                padding:10px;
            }
            td {
                text-align: center;
                padding:10px;
            }
            #back { 
                text-align:left;
                margin : 10px 10px;    
                    height:100px;
           }
            #logout { 
                text-align:right;
                margin : 10px 10px;       
                    height:100px; 
           }
           #button {
                width : 100%;
                border : none;
           }  
        </style>
    </head>
    <body>
       <table id="button">
        <tr>
            <td>
		<form id="back">
		    <button class="btn btn-default" name="customer" value="backtoAdmin" formMethod="Post"
		        formaction="/CustomertoAdmin"><i class="fa fa-arrow-circle-left"></i>&nbsp;Go-Back</button>
                </form>
            </td>
            <td>
                <form id="logout">
                    <button type="submit" class="btn btn-default" name="login" value="logout"
                        formaction="/signOut" formmethod="Post">logout&nbsp;<i class="fa fa-sign-out"></i> 
                    </button>
                </form>
            </td>
        </tr>
        </table>
        <form method="Post">
        <div>
            <table align="center">
                <tr>
                    <th colspan="5" class="title">CUSTOMER DETAILS</th>
                </tr>
                <tr>
                    <th>Customer Id</th>
                    <th>Name</th>
                    <th>Mobile No</th>
                    <th>Email ID</th>
                    <th>Address</th>
                </tr>
                <c:forEach items="${customers}" var="customer">
                    <tr>
                        <td><c:out value="${customer.id}" /></td>
                        <td><c:out value="${customer.name}" /></td>
                        <td><c:out value="${customer.mobileNumber}" /></td>
                        <td><c:out value="${customer.mailId}" /></td>
                        <td>
                            <form>
                            <input type="hidden" name="id" value="${customer.id}">
                            <button type="submit" name="customer"  formaction="CustomerDetail" formmethod="Post"
                                value="/viewAddress" class="btn btn-default"> View Address </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        </form>
    </body>
    <script type="text/javascript">
        function fnGoBack() {
             window.history.back();
        }
    </script>
<html>
