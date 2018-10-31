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
            margin-top : 10%;
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
    <body onload="customerPage()">
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
            <tr>
                <td colspan="2">
                    <div id="user">
                           Welcome&nbsp;&nbsp;${name}!!!
                           <input type="hidden" value="${userid}" readonly>
                    </div>
                </td>
            </tr>
        </table>
        <form>
            <table align="center" >
                <tr>
                    <th>
                          USER MENU
                    </th>
                </tr>
                <tr>
                    <td> 
                        <button class="btn" name="customer" formmethod="POST" formaction="myAccount"
                            value="/updateCustomer">My Account</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button class="btn" name="customer" formmethod="POST" formaction="purchaseDvd"
                            value="/purchaseDvd" >Purchase Dvd</button> 
                    </td>
                </tr>
                <tr>
                    <td> 
                        <button class="btn" name="customer" formmethod="POST" formaction="myOrders"
                            value="/myOrders">My Orders</button> 
                    </td>
                </tr>
            </table>
        </form>
    </body>

    <script type="text/javascript">
        function customerPage() {
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
    </script>
</html>

