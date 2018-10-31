<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html>
    <head>
        <title>Dvd Store</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <style>
            body {
                text-align : center;
            }
            h2 {
                color : blue;
                font-style: italic;
                text-decoration: underline;
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

    <body onload="searchResult()">
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
        <h2>Search Dvd</h2>
        <form>
            <label for="name"> Search Dvd By Name :</label>
            <input type="text" name="name" value="${name}" required> 
            <button type="submit" name="dvd" formmethod="POST" formaction="/searchDvdByName"value="searchDvdByName" 
                class="btn btn-success">Search</button>
        </form><br/>
        <form>
            <label for="name"> Search Dvd By ID : </label>
            <input type="text" name="dvdid" value="${id}" maxlength="6" required> 
            <button type="submit" name="dvd" formmethod="POST" formaction="/searchDvdById" value="searchDvdById" 
                class="btn btn-success">Search</button>
        </form>
    </body>
    
    <script type="text/javascript">
        function searchResult() {
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


</html>    
