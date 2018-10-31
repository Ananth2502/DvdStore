<!DOCTYPE html>

<html>
    <head>
        <title>Dvd Store</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
        table {
            margin-top : 8%;
        }
        table,th { 
           width:50%;
           border: 3px solid black;
        }
        td, th {
           text-align : center;
        }
        th {
           font-weight:bold;
           color: blue;
           height: 40px;
        }
        #logout { 
            text-align:right;
            margin : 10px 10px;        
        }
        #button {
            width:100%;
            border:none;
            margin-top : 0%;
        } 
        </style>
    </head>
    <body onload="adminPage()">
        <table id="button">
            <tr>
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
            <table align="center" >
                <tr>
                    <th> ADMIN MENU </th>
                </tr>
                <tr>
                    <td>
                         <button class="btn" name="dvd" value="dvd" formmethod="Post" formaction="DvdMenu">
                             Dvd Menu</button>
                     </td>
                </tr>
                <tr>
                    <td> 
                        <button class="btn" name="category" value="category" formmethod="Post"
                            formaction="/CategoryMenu">Category Menu</button> 
                    </td>
                </tr>
                <tr>
                    <td> 
                        <button class="btn" name="customer" formaction="SearchCustomer" formmethod="Post"
                            value="/searchCustomer" >Search Customer</button> 
                    </td>
                </tr>
                <tr>
                    <td> 
                        <button class="btn" name="customer" value="displayAllCustomers" formmethod="Post"          
                            formaction="/displayAllCustomers" >Show Customers</button> 
                    </td>
                </tr>
                <tr>
                    <td> 
                        <button class="btn" name="customer" value="displayOrders" formmethod="Post"          
                            formaction="/displayAllOrders">Show Orders</button> 
                    </td>
                </tr>
            </table>
        </form>
    </body>

    <script type="text/javascript">
        function adminPage() {
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
    </script>
</html>

