<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<html>
    <head>
        <title>Dvd Store</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">	
        <style>
            table { 
               width : 50%;
            }
            th {
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
            .line {
                height:5px;
            }
            .text { 
                font-weight:bold;
            }
            input[type] {
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
	        border:none;
	   } 
        </style>
    </head>
    <body onload="categoryCreationResult()">
        <table id="button">
            <tr>
                <td>
		    <form id="back">
		        <button class="btn btn-default" name="category" value="backtoCategory" formMethod="Post"
		            formaction="/CategoryMenu"><i class="fa fa-arrow-circle-left"></i>
                            &nbsp;Go-Back</button>
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
        <form:form align="center" modelAttribute="category">
            <table align="center">
                <tr> 
                    <th colspan="0.5"></th>
                    <th colspan="1.5">Enter category details...</th>
                </tr>
                <tr>
                    <td>Name :</td>
                    <td class="input">
                        <form:hidden path="id" name="categoryid" value="${category.id}" />
                        <form:input path="name" id="category" name="name"
                           value="${category.name}" maxlength="30" required="required" />
                    </td>
                </tr>
                <tr class="line"></tr>
                <tr>
                    <td></td>
                    <td class="input"> 
                        <c:if test="${button == 'save'}">
                            <button type="submit" name="category" value="save" formmethod="Post"
                                formaction="/saveCategory" class="btn btn-success">Save</button>
                        </c:if>
                        <c:if test="${button == 'update'}">
                            <button type="submit" name="category" value="update" formmethod="Post"
                                formaction="/updateCategory" class="btn btn-info">Update</button>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form:form>
    </body>
    <script>
        function categoryCreationResult() {
            var message = "${message}";
            var status = "${status}";
            if (status == 'success' || status == 'fail') {
               alert(message);
            }
        }
    </script>
 
</html>
