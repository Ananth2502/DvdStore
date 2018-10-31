<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<html>
    <head>
        <title>Dvd Store</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            th, h3 {
                text-align: center;
                font-weight: bold;
                font-size: 20px;
                font-style :italic; 
                color: blue;
                height: 40px;
                width: auto;
                padding:10px;

            }
            table,th {
                margin-top:2%;
                border : 2px solid black;
            }
            td {
                text-align:center;
                padding:10px;
            }
            .line {
                height:5px;
            }
            .text { 
                font-weight:bold;
            }
            label {
                width: 150px;  
                text-align:left;
                margin-top:1%;
            }  
            form, div {
                text-align:center;    
            }
            input[type] {
                width : 20%;
            }
            .modal-header {
                height : 100px;
            }
            .modal-footer {
                text-align: center;
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
                margin-top:0%;
           } 
        </style>
    </head>

    <body onload="validateResult()">
       <table id="button">
        <tr>
            <td>
		<form id="back">
		    <button class="btn btn-default" name="category" value="backtoCustomer" formMethod="Post"
	                formaction="/backtoCustomer"><i class="fa fa-arrow-circle-left"></i>&nbsp;Go-Back</button>
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
        <form:form id="customer" modelAttribute="customer">
        <div>
            <h3 colspan="2">My Account</h3><br/>
            <label>Customer ID</label>:
            <input type="number" path="id" name="customerId" value="${customer.id}" readonly><br/>
            <label>Name </label>:
            <input type="text" path="name" name="name" value="${customer.name}"
                required="required"><br/>
            <label>Mobile no </label>:
            <input type="number" path="mobileNumber" maxlength="15" value="${customer.mobileNumber}"
                name="mobileNumber" pattern="[0-9]" required="required"> <br/>
            <label>Email Id </label>:
            <input type="text" path="mailId" name="mailId" value="${customer.mailId}"
                pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required="required"><br/>
        </div>
        <table align="center">
             <tr>
                 <th>Address ID</th>
                 <th>Street</th>
                 <th>City</th>
                 <th>State</th>
                 <th>Pincode</th>
                 <th>Options</th> 
             </tr>

<c:forEach items="${customer.addresses}" var="address">
<tr>
    <td><c:out value="${address.id}" /></td>
    <td><c:out value="${address.street}" /></td>
    <td><c:out value="${address.city}" /></td>
    <td><c:out value="${address.state}" /></td>
    <td><c:out value="${address.pincode}" /></td>
    <td>
        <form:form modelAttribute="address">
            <input type="hidden" path="customerId" name="customerId" value="${customer.id}">
            <input type="hidden" path="id" name="addressId" value="${address.id}">
            <button type="button" class="btn btn-primary" data-toggle="modal" 
                data-target="#${address.id}">Edit</button>
            <div class="modal fade" id="${address.id}" role="dialog">
                <div class="modal-dialog modal-lg">   
                <div class="modal-content">
                    <div class="modal-header"><h1>Address</h1></div>
                    <div class="modal-body">
                        <label>Street</label>:
                        <input type="text" path="street" name="street" pattern="[A-Za-z0-9]{}" width="200px"
                            maxlength="100" value="${address.street}" required="requierd"><br/> 
                        <label class="address">City</label>:
                        <input type="text" path="city" name="city" value="${address.city}" maxlength="100" 
                            required="required"><br/>
                        <label class="address">State</label>:
                        <input type="text" path="state" name="state" value="${address.state}" maxlength="50" 
                            required="required"><br/>
                        <label class="address">Pincode</label>:
                        <input type="number" path="pincode" maxlength="6" name="pincode" 
                            onchange="validatePincode()" value="${address.pincode}" required="required">
                    </div>
                    <div class="modal-footer" text-align="center">
                        <button type="submit" name="customer" value="updateAddress" 
                            formaction="/updateAddress" formmethod="Post"class="btn btn-success">
                            Save </button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">
                            Cancel</button>
                    </div>
                </div>
                </div>
            </div>
            <button type="submit" formaction="/deleteAddress" formmethod="Post" value="deleteAddress"
                name="customer" onclick="return confirm('Are you sure want to delete?');" formmethod="Post"
                class="btn btn-danger"> Delete </button>
        </form:form>
    </td>
</tr>
</c:forEach>

            </table>
            <br/><br/> 
            <form:form modelAttribute="newAddress">
            <input type="hidden" path="customerId" name="customerId" value="${customer.id}">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addAddress">
                Add Another Address</button>
            <div class="modal fade" id="addAddress" role="dialog">
                <div class="modal-dialog modal-lg">   
                <div class="modal-content">
                    <div class="modal-header" ><h1>Address</h1></div>
                    <div class="modal-body">
                        <label>Street</label>:
                        <form:input type="text" path="street" maxlength="100" width="200px" 
                            required="required" /><br/> 
                        <label class="address">City</label>:
                        <form:input type="text" path="city" maxlength="100" required="required"/><br/>
                        <label class="address">State</label>:
                        <form:input type="text" path="state" maxlength="50" required="required"/><br/>
                        <label class="address">Pincode</label>:
                        <form:input type="number" path="pincode" maxlength="6" onchange="validatePincode()"
                            id="pincode" name="newPincode" required="required" />
                    </div>
                    <div class="modal-footer" text-align="center">
                        <button type="submit" name="customer" value="newAddress" formaction="/addAddress"
                            formmethod="Post" class="btn btn-success"> Save </button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal"> Cancel </button>
                    </div>
                </div>
                </div>
            </div>
            </form:form><br/>
            <div>
                  <button form="customer" type="submit" name="customer" formaction="/updateCustomer"
                      formmethod="Post" value="update" class="btn btn-success">Update</button>        
            </div>
    </form:form>
    </body>

    <script type="text/javascript">
        function validateResult() {
            var message = "${message}";
            var emptyMessage = "${emptyMessage}";
            var status = "${status}";
            var isEmpty = "${isEmpty}";
            if (status == 'fail' || status=='success') {
               alert(message);
            }
            if (isEmpty == 'empty') {
                alert(emptyMessage);
            }
        }

        function validatePincode() { 
            var pincode = document.getElementById("pincode");
            var numbers = /^[0-9]{6}$/;
            if(!(pincode.value.match(numbers))) {
                alert('Pin code must have numeric characters only');
                document.getElementById("pincode").value="";
                pincode.focus();
            }
        }

        function fnGoBack() {
             window.history.back();
        }

    </script>
</html>
