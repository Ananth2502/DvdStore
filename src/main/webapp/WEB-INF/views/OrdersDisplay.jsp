<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<html>
<head>
    <title>Dvd Store</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        .main { 
          text-align : center;
        }
        h2 {
           color : blue;
           text-decoration-color: black;
           text-decoration-line: underline;
           text-decoration-style :double;
        }
        .title {
            color:black;
        }
        label {
            width: 150px;  
        }
        table {
            overflow : auto; 
        }
        table,th {
            border : 2px solid black;
        }
        th {
            text-align: center;
            font-style :italic; 
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
        .order {
            overflow-y :auto;
            height: 450px;
            text-align: center;     
        }
        .purchase {
             text-align: center;
        }
        .customer {
            text-align:left;
            margin-left:25%;    
        }
        .modal-footer {
            text-align: center;
        }
        #backtoAdmin { 
            text-align:left;
            margin : 10px 10px;        
       }
        #backtoCustomer { 
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
<body onload="orderResult()">
       <table id="button">
        <tr>
            <td>
               <c:if test="${user == 'admin'}">
		<form id="backtoAdmin">
		    <button class="btn btn-default" name="customer" value="backtoAdmin" formMethod="Post"
		        formaction="/CustomertoAdmin"><i class="fa fa-arrow-circle-left"></i>&nbsp;Go-Back</button>
                </form>
                </c:if>
               <c:if test="${user != 'admin'}">
		<form id="backtoCustomer">
		    <button class="btn btn-default" name="customer" value="backtoAdmin" formMethod="Post"
		        formaction="/backtoCustomer"><i class="fa fa-arrow-circle-left"></i>&nbsp;Go-Back</button>
                </form>
                </c:if>
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
    <form class="main" method="Post"><br/> 
        <c:if test="${user == 'admin'}">
            <h2>Order Details</h2>
        </c:if>
        <c:if test="${user != 'admin'}">
        <h2>My orders</h2>
        <div class="customer">
            <c:set value="1" var="value" />
                <c:forEach items="${orders}"  var="order"> 
                    <c:if test="${value == '1'}">
                        <c:set value="${order.customer}" var="customer" />
                        <label>Customer Id</label>: <c:out value="${customer.id}"/><br/>
                        <label>Name</label>: <c:out value="  ${customer.name}"/><br/>
                        <label>Mobile number</label>: <c:out value="  ${customer.mobileNumber}"/><br/>
                        <label>Email Id</label>: <c:out value="  ${customer.mailId}" /><br/>
                        <c:set value="0" var="value" />
                    </c:if>
                </c:forEach>
            </c:if>
        </div>
        <br/>
        <div class="order">
        <table align="center">
            <tr>
                <th>Order Id</th>
                <c:if test="${user == 'admin'}">
                    <th>Customer</th>
                </c:if>
                <th>Total Price</th>
                <th>Order date</th>
                <th>Delivery Address</th>
                <th>Ordered Dvds</th>
                <c:if test="${user != 'admin'}">
                    <th>Cancel Order</th>
                </c:if>
            </tr>
            <c:forEach items="${orders}" var="order">
                <tr> 
                    <td><c:out value="${order.id}" /></td>
                    <c:if test="${user == 'admin'}">
                    <td>
                        <form>
                            <c:set var="customer" value="${order.customer}" />
                            <button type="button" class="btn btn-default" data-toggle="modal"s
                                data-target="#${customer.id}">${customer.name}</button>
                            <div class="modal fade" id="${customer.id}" role="dialog">
                                <div class="modal-dialog modal-md"">   
                                <div class="modal-content">
                                    <div class="modal-header"><h3>Customer Details</h3></div>
                                     <div class="modal-body">
                                         <table>
                                         <tr>
                                             <th>Customer Id</th>
                                             <th>Name</th>
                                             <th>Mobile Number</th>
                                             <th>Email Id</th>
                                         </tr>
                                         <tr>
                                             <td><c:out value="${customer.id}" /></td>
                                             <td><c:out value="${customer.name}" /></td>
                                             <td><c:out value="${customer.mobileNumber}" /></td>
                                             <td><c:out value="${customer.mailId}" /></td>
                                         </tr>
                                         </table>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">
                                             close</button>
                                    </div>
                                </div>
                                </div>
                            </div>
                        </form>   
                    </td>
                    </c:if>
                    <td><c:out value="Rs.${order.totalPrice}" /></td>
                    <td><c:out value="${order.orderDate}" /></td>
                    <td>
                        <c:set var="address" value="${order.address}" />
                         ${address.street}, ${address.city}, ${address.state},
                         ${address.pincode}
                    </td>
                    <td>
                        <form>
                            <button type="button" class="btn btn-default" data-toggle="modal" 
                                data-target="#${order.id}">Dvd</button>

                            <div class="modal fade" id="${order.id}" role="dialog">
                                <div class="modal-dialog modal-lg"">   
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h3>Ordered Dvds</h3>
                                    </div>
                                    <div class="modal-body">
                                        <table align="center" width="100%">
                                        <tr>
                                            <th>Dvd Id</th>
                                            <th>Name</th>
                                            <th>Price</th>
                                            <th>Rating</th>
                                            <th>Language</th>
                                            <th>Release Date</th>
                                            <th>Category</th> 
                                        </tr>
                                        <c:forEach items="${order.dvds}" var="dvd">
                                        <tr>
                                            <td><c:out value="${dvd.id}" /></td>
                                            <td><c:out value="${dvd.name}" /></td>
                                            <td><c:out value="${dvd.price}" /></td>
                                            <td><c:out value="${dvd.rating}" /></td>
                                            <td><c:out value="${dvd.language}" /></td>
                                            <td><c:out value="${dvd.releaseDate}" /></td>
                                            <td>
                                            <c:forEach items="${dvd.categories}" var="category">
                                                <input type="hidden" value="${category.id}">
                                                <c:out value="${category.name}"/>&nbsp;
                                            </c:forEach>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                        </table>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" 
                                         data-dismiss="modal">close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </form>    
                    </td>
                    <c:if test="${user != 'admin'}">
                    <td> 
                        <form>
                            <input type="hidden" name="id" value="${order.id}">
                            <button type="submit" name="customer"  
                                onclick="return confirm('Are you sure?? do you want to cancel this order?');"     
                                formaction="cancelOrder" formmethod="Post" value="cancelOrder" 
                                class="btn btn-warning"> Cancel Order </button>
                        </form>
                    </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        </div>
    </form>
</body>
<script>
    function orderResult() {
        var message = "${message}";
        var emptyMessage = "${emptyMessage}";
        var status =     "${status}";
        var isEmpty = "${isEmpty}";
        if (status == 'fail' || status=='success') {
           alert(message);
        }
        if (isEmpty == 'empty') {
            alert(emptyMessage);
        }
    }
    function fnGoBack() {
         window.history.back();
    }
</script>
<html>
