<!DOCTYPE html>

<html>
    <head>
        <title>Dvd Store</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
        .btn {
             border: none;
             background-color: inherit;
             padding: 14px 28px;
             font-size: 16px;
             display: inline-block;
        }
        .btn:hover {
            background: #eee;
        }
        form {
            margin-top :8%;
        }
        table,th { 
           width:50%;
           border: 3px solid black;
        }
        td, th{
           text-align : center;
        }
        th {
           font-weight:bold;
           color: blue;
           height: 40px;
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
    <body onload="categoryResult()">
        <table id="button">
            <tr>
                <td>
                    <form id="back">
                         <button class="btn btn-default" name="category" value="backtoAdmin" formMethod="Post"
                             formaction="/CategorytoAdmin"><i class="fa fa-arrow-circle-left"></i>
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
        <form>
            <table align="center">
                <tr>
                    <th> CATEGORY MENU </th>
                </tr>
                <tr>
                    <td>
                        <button class="btn" name="category" value="addCategory" formmethod="Post"
                            formaction="/CreateCategory">Add Category</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button class="btn" name="category" value="displayCategory" formmethod="Post"
                            formaction="/displayCategory">View Categories</button> 
                    </td>
                </tr>
                <tr>
                    <td> 
                        <button class="btn" name="category" value="restoreCategory" formmethod="Post"
                            formaction="/displayInactiveCategory">Restore Category</button> 
                    </td>
                </tr>
            </table>
        </form>
    </body>
    <script>
        function categoryResult() {
            var message = "${message}";
            var status = "${status}";
            var isEmpty = "${isEmpty}";
            if (status == 'fail' || status=='success') {
               alert(message);
            }
            if (isEmpty == 'empty') {
                alert(message);
            }
        }

        function fnGoBack() {
         window.history.back();
        }
    </script>

</html>

