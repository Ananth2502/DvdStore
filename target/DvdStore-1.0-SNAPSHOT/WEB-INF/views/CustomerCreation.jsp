<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<html>
    <head>
        <title>Dvd Store</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            table { 
               width : 50%;
            }
            th {
                text-align: center;
                font-weight: bold;
                font-size: 20px;
                color: blue;
                height: 40px;
            }
            td {
                text-align:right;
                padding:10px;
            }
            .input {
                text-align:left;    
            }
            label {
                width : 150px;
                text-align:left;    
            }
            .line {
                height:5px;
            }
            input[type=date],input[type=number] {
                width:175px; 
            }
            body {
               background-image: url("neww.png");
               background-repeat: no-repeat;
               background-position: left center;
               background-attachment: fixed;
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

    <body onload="creationResult()">
       <table id="button">
        <tr>
            <td>
                <form id="back">
                    <button class="btn btn-default" name="login" value="LoginPage" formMethod="Post"
                        formaction="/signOut"><i class="fa fa-arrow-circle-left"></i>&nbsp;Go-Back</button>
                 </form>
            </td>
        </tr>
        <form:form modelAttribute="customer">
            <table align="center">
                <tr>
                </tr>
                <tr>
                    <td><label>Name </label>:</td>
                    <td class="input">
                        <form:input type="text" path="name" id="name" name="name" maxlength="30"
                            required="required" />
                    </td>
                </tr>
                <tr class="line"></tr>
                <tr>
                    <td><label>User Name </label>:</td>
                    <td class="input">
                        <form:input type="hidden" path="user.role" name="userId" data-toggle="tooltip"
                            value="CUSTOMER" required="required" />
                        <form:input type="text" path="user.userId" id="userId" name="userId" data-toggle="tooltip" 
                           data-placement="right" title="Username contains letters and numbers"
                            required="required" />
                    </td>
                </tr>
                <tr class="line"></tr>
                <tr>
                    <td><label>Mobile no </label>:</td>
                    <td class="input">
                        <form:input type="text" path="mobileNumber" maxlength="15" id="mobileNumber"
                           name="mobileNumber" data-toggle="tooltip" data-placement="right" 
                           pattern="[1-9]{1}[0-9]{9}" 
                           title="Mobile number must be 10 numbers" required="required" />
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td><label>Email Id </label>:</td>
                    <td class="input">
                        <form:input type="text" path="mailId" id="mailId" name="mailId"  maxlength="50"
                            pattern="^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$" required="required" /> 
                    </td>    
                </tr>
                <tr class="line"></tr>
                <tr>
                    <td><label>Password </label>:</td>
                    <td class="input">
                        <form:password id="pwd" path="user.password" name="password" data-toggle="tooltip" 
                            data-placement="right" required="required"
                            title="Make strong password with numbers, letters and special charactrs"  /> 
                    </td>
                </tr>
                <tr class="line"></tr>
                <tr>
                    <td><label>Confirm Password </label>:</td>
                    <td class="input">
                        <input type="password" id="cfmpwd" data-toggle="tooltip" 
                            data-placement="right"
                            title="Enter same password again" onchange="validatePassword()" required="required" /> 
                    </td>
                </tr>
                <tr class="line"></tr>
                <tr>
                    <td><label>Street </label>:</td>
                    <td class="input">
                        <form:input type="text" path="addresses[0].street" name="street" maxlength="100"
                            required="required" /> 
                    </td>
                </tr>
                <tr class="line "></tr>
                <tr>
                    <td><label>City </label>:</td>
                    <td class="input">
                        <form:input type="text" path="addresses[0].city" name="city" maxlength="100"
                            required="required" />
                    <td>
                </tr>
                <tr class="line"></tr>
                <tr>
                    <td><label>State </label>:</td>
      	              <td class="input"> 
                        <form:input type="text" path="addresses[0].state" name="state" maxlength="50"
                            required="required" />
                    </td>
                </tr>
                <tr class="line"></tr>
                <tr>
                    <td><label>Pincode </label>:</td>
                    <td class="input">
                        <form:input type="text" path="addresses[0].pincode" maxlength="6" id="pincode"
                            name="pincode" onchange="validatePincode()" data-toggle="tooltip" 
                            data-placement="right" title="Pincode must be 6 numbers" required="required" />
                    </td>
                </tr>
                <tr class="line"></tr>
                <tr>
                    <td></td>
                    <td class="input" id="save"> 
                        <button type="submit" name="login" value="save" class="btn btn-success" formmethod="Post"
                            formaction="/AddCustomer">Register</button>
                    </td>
                </tr>
            </table>
        </form:form>

    
    <script type="text/javascript">

        function creationResult() {
            var messge = "${message}";
            var status = "${status}";
            if (status == 'success' || status == 'fail') {
               alert(message);
            }
        }

        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();   
        });

        function validateEmail() {
            var mailId = document.getElemetById("mailId");
            var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            if (!(mailId.value.match(mailformat))) { 
                 alert("You have entered an invalid email address!");
                 document.getElemetById("mailId") = "";
                 mailId.focus();
              }
        }

        function validatePassword() {
            var password = document.getElementById("pwd").value;
            var confirmPassword = document.getElementById("cfmpwd").value;
            if (password != confirmPassword) {
                alert("Password and Confirm password does not match");
                document.getElementById("cfmpwd").value="";
                password.focus();
                return;
            }
        }

        function validatePincode() { 
            var pincode = document.getElementById("pincode");
            var numbers = /^[0-9]+$/;
            if(!(pincode.value.match(numbers))) {
                alert('Pin code must have numeric characters only');
                document.getElementById("pincode").value="";
                pincode.focus();
            }
        }

    </script>


    </body>

</html>
