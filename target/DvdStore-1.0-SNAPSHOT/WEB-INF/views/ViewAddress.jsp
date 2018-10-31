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
            .title {
                color:blue;
            }
            table {
                overflow : auto; 
                width : 80%;
            }
            table, th {
                border : 2px solid black;
            }
            th {
                text-align: center;
                font-size: 20px;
                color: blue;
                height: 30px;
                width: auto;
                padding:10px;
            }
            td {
                text-align: center;
                padding:0px 10px;
            }
            #customer {
                border : none;
                text-align : right;
                width : auto;
                font-size : 20px;
            }
            #customer th {
                border : none;
                text-decoration : underline;
                font-size : 20px;
            }
            .text {
                text-align:left;
            }
            td {
                text-align: center;
                padding:10px;
            }
            #back { 
                text-align:left;
                margin : 10px 10px;        
           }
            #logout { 
                text-align:right;
                margin : 10px 10px;        
           }
           #button {
                width:100%;
                border:none;
           } 
        </style>
    </head>
    <body>
        <table id="button">
        <tr>
            <td>
                <div id="back">
                    <button id="back" class="btn btn-default" onclick="fnGoBack()" formMethod="Post">
                        <i class="fa fa-arrow-circle-left"></i>&nbsp;Go-Back</button>
                </div>
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
        <form method="Post" items="${customer}">
            <table align="center" id="customer">
                <tr>
                    <th colspan="3" class="title"><i>CUSTOMER DETAILS</i></th>
                </tr>
                <tr>
                    <td class="text">Customer Id</td>
                    <td>:</td>
                    <td> <input type="text" name="name" value="${customer.id}" readonly/></td>
                </tr>
                <tr>
                    <td class="text">Name</td>
                    <td>:</td>
                    <td class="input">
                        <input type="text" name="name" value="${customer.name}"  
                            pattern="[A-Za-z]{}" readonly>
                    </td>
                </tr>
                <tr>
                    <td class="text">Mobile No</td>
                    <td>:</td>
                    <td>                        
                        <input type="number" maxlength="15" name="mobileNumber" 
                            value="${customer.mobileNumber}" pattern="[0-9]" readonly>
                    </td>
                </tr>
                <tr>
                    <td class="text">Email ID</td>
                    <td>:</td>
                <td>                       
                        <input type="text" name="mailId" value="${customer.mailId}"
                            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" readonly> 
                    </td>
                </tr>
            </table>
            <br/><br/>
            <table align="center">
                <tr>
                    <th class="address">Address</th>
                </tr>
                <tr>
                    <th>Street</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Pincode</th>
                </tr>
                <c:forEach items="${customer.addresses}" var="address">
                    <tr>
                        <td><c:out value="${address.street}" /></td>
                        <td><c:out value="${address.city}" /></td>
                        <td><c:out value="${address.state}" /></td>
                        <td><c:out value="${address.pincode}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
    <script type="text/javascript">
         function fnGoBack() {
             window.history.back();
         }
    </script>
<html>
