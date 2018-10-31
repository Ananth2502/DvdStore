<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<html>
    <head>
        <title>Dvd Store</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            th {
                text-align: center;
                font-weight: bold;
                font-size: 20px;
                color: blue;
                height: 40px;
            }
            td {
                text-align:left;
                padding:10px ;
            }
            .input {
                text-align:left;    
            }
            .line {
                height:5px;
            }
            td label { 
                width: 100px;
            }
            input[type=date],input[type=number],input[type=text] {
                width:200px; 
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
           }
        </style>
    </head>

    <body onload="dvdCreationResult()">
       <table id="button">
        <tr>
            <td>
                <form id="back">
                    <button class="btn btn-default" name="dvd" value="backtoDvd" formMethod="Post"
                        formaction="/DvdMenu"><i class="fa fa-arrow-circle-left"></i>&nbsp;Go-Back</button>
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
        <form:form method="POST" modelAttribute="dvd">
            <table align="center">
                <tr>
                    <th colspan="2">Enter Dvd details</th>
                </tr>
                <tr>
                    <td><label>Name </label>:</td>
                    <td class="input">
                        <form:hidden path="id" value="${dvd.id}" />
                        <form:input path="name" value="${dvd.name}" required="required" />
                    </td>
                </tr>
                <tr class="line"></tr>
                <tr>
                    <td><label>Price </label>:</td>
                    <td class="input">
                        <form:input type="number" path="price" min="0" name="price" maxlength="5"
                            value="${dvd.price}" pattern="[0-9]" required="required" />
                    </td>
                </tr>
                <tr class="text"></tr>
                <tr>
                    <td><label>Quantity </label>:</td>
                    <td class="input">
                        <form:input type="number" path="quantity" min="0" name="quantity" maxlength="10" 
                            value="${dvd.quantity}" required="required" /> 
                    </td>    
                </tr>
                <tr class="line"></tr>
                <tr>
                    <td><label>Rating </label>:</td>
                    <td class="input">
                        <form:input type="number" path="rating" min="0.1" max="10" step="0.1" name="rating"
                            value="${dvd.rating}" required="required" /> 
                    </td>
                </tr>
                <tr class="line "></tr>
                <tr>
                    <td><label>Release Date </label>:</td>
                    <td class="input">
                        <form:input type="date" path="releaseDate" id="releaseDate"
                            value="${dvd.releaseDate}" onblur="isFutureDate(event)" required="required" />
                    <td>
                </tr>
                <tr class="line"></tr>
                <tr>
                    <td><label>Language </label>:</td>
                    <td class="input"> 
                        <form:input type="text" path="language" name="language" maxlength="30"
                            value="${dvd.language}" required="required"/>
                    </td>
                </tr>
                <tr class="line"></tr>
                <tr>    
                    <td valign="top" ><label>Categories </label>:</td>

                    <td class="input">
                    <c:forEach items="${categories}" var="category" >
                        <c:set var="check" value="1" />
                        <c:forEach items="${dvd.categories}" var="dvdCategory">
                            <c:if test="${category.id == dvdCategory.id}">
                                <label style="width:100%;font-weight:normal;text-style:italic">
                                    <form:input type="checkBox" checked="checked" path="categories" name="check"
                                      value="${category.id}" />
                                     &nbsp;<c:out value="${category.name}" />
                                    <c:set var="check" value="0" />
                                </label><br/>
                           </c:if>
                        </c:forEach>
                        <c:if test="${check == '1'}">
                            <label style="width:100%;font-weight:normal;text-style:italic">
                                <form:input type="checkBox"  path="categories" name="check" 
                                    value="${category.id}" />&nbsp;
                                <c:out value="${category.name}" />
                            </label><br/>
                        </c:if>
                    </c:forEach>
                    </td>
                </tr> 
                <tr class="line"></tr>
                <tr>
                    <td></td>
                    <td class="input" > 
                        <c:if test="${button == 'save'}">
                            <button type="submit" name="dvd" formmethod="Post" formaction="/saveDvd" value="save"
                                onclick="validateForm(event)" class="btn btn-success">Save</button>
                        </c:if>
                        <c:if test="${button == 'update'}">
                            <button type="submit" name="dvd" formmethod="Post" formaction="/updateDvd"
                                onclick="validateForm(event)" value="update" class="btn btn-info">Update</button>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form:form>
    </body>
    
    <script>
        function dvdCreationResult() {
            var message = "${message}";
            var status = "${status}";
            if (status == 'fail' || status=='success') {
               alert(message);
            }
        }

        function validateForm(event) {
            var checked = false;
            var c = document.getElementsByTagName('input'); 
            for (var i = 0; i < c.length; i++) { 
                if (c[i].type == 'checkbox' && c[i].checked == true) { 
                    checked = true; 
                }
            }
            if (checked == false) {
                event.preventDefault();
                alert("select atleast one category");
                return;
            }
        }

        function isFutureDate(event) {
            event.preventDefault();
            var releaseDate = document.getElementById("releaseDate").value;
            var today = new Date();
            if ((new Date(releaseDate).getTime() > new Date(today).getTime())) {
                alert("Release date cannot be future..");
                document.getElementById("releaseDate").value ="";
            }
        }

        function fnGoBack() {
         window.history.back();
        }
    </script>


</html>
