<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<html>
    <head>
        <title>Dvd Store</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .title {
                color:black;
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
                <form id="back">
                    <button class="btn btn-default" name="category" value="backtoCategory" formMethod="Post"
                        formaction="/CategoryMenu"><i class="fa fa-arrow-circle-left"></i>&nbsp;Go-Back</button>
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
            <table align="center">
                <tr>
                    <c:if test="${display == 'displayCategory'}">
                        <th colspan="3" class="title">CATEGORY DETAILS</th>
                    </c:if>
                    <c:if test="${display == 'restoreCategory'}">
                        <th colspan="3" class="title">INACTIVE CATGEORY DETAILS / TRASH DETAILS</th>
                    </c:if>
                </tr>
                <tr>
                    <th>Category Id</th>
                    <th>Name</th>
                    <th>Options</th>
                </tr>
                <c:forEach items="${categories}" var="category">
                    <tr>
                        <td><c:out value="${category.id}" /></td>
                        <td><c:out value="${category.name}" /></td>
                        <td>
                            <c:if test="${display == 'displayCategory'}">
                            <form> 
                                <input type="hidden" name="id" value="${category.id}">
                                <button type="submit" name="category" value="deleteCategory" 
                                    onclick="return confirm('Are you sure want to delete?');"
                                    formaction="/deleteCategory" formmethod="Post"  
                                    class="btn btn-danger"> Delete </button>
                                <button type="submit" name="category" value="updateCategory"
                                     formaction="/modifyCategory" formmethod="Post"
                                     class="btn btn-primary"> Edit </button>
                                <button type="submit" name="category" value="searchDvdByCategory"
                                     formaction="/searchDvdByCategory" formmethod="Post"
                                     class="btn btn-success"> View Dvds </button>
                            </form>
                            </c:if>
                            <c:if test="${display == 'restoreCategory'}">
                            <form> 
                                <input type="hidden" name="id" value="${category.id}">
                                <button type="submit" name="category" value="restoreCategoryById"
                                    formaction="/restoreCategory" formmethod="Post" 
                                    class="btn btn-danger"> Restore </button>
                            </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
    <script>
        function dvdResult() {
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

        function fnGoBack() {
            window.history.back();
        }
    </script>
<html>
