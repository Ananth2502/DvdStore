<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<html>
<head>
    <title>Dvd Store</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/pretty-checkbox@3.0/dist/pretty-checkbox.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        form { 
          text-align : center;
        }
        .title {
            color:black;
        }
        table {
            overflow : auto; 
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
        .table {
            overflow-y :auto;
            height: 400px;
            text-align: center;     
        }
        .purchase {
             text-align: center;
        }
        .back { 
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
<body onload="dvdResult()" >
    <table id="button">
    <tr>
        <td>
            <c:if test="${display == 'displayDvd' && back != 'DvdFromcategory'}">
            <form class="back">
		<button class="btn btn-default" name="dvd" value="backtoDvd" formMethod="Post"
		    formaction="/DvdMenu"><i class="fa fa-arrow-circle-left"></i>&nbsp;Go-Back</button>
 	    </form>
            </c:if>
            <c:if test="${display == 'restoreDvd'}">
            <form class="back">
		<button class="btn btn-default" name="dvd" value="backtoDvd" formMethod="Post"
		    formaction="/DvdMenu"><i class="fa fa-arrow-circle-left"></i>&nbsp;Go-Back</button>
 	    </form>
            </c:if>
            <c:if test="${back == 'DvdFromcategory' && display == 'displayDvd'}">
            <form class="back">
		<button class="btn btn-default" name="dvd" value="backtoDvd" formMethod="Post"
		    formaction="/displayCategory"><i class="fa fa-arrow-circle-left"></i>&nbsp;Go-Back</button>
	    </form>
            </c:if>
            <c:if test="${display == 'purchaseDvd'}">
            <form class="back">
		<button class="btn btn-default" name="dvd" value="backtoDvd" formMethod="Post"
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
    <form ><br/>
        <c:if test="${display == 'displayDvd'}">
            <form>
                <input type="text" name="name" formmethod="POST" formaction="Dvd" 
                    placeholder="Enter name to search dvd..." required> 
                <button type="submit" name="dvd" formmethod="POST" formaction="/searchDvdByName"
                    value="searchDvdByName" class="btn btn-success">Search</button>
            </form><br/>
            <form>
                <input type="text" name="dvdid" placeholder="Enter dvd id to search dvd..." required> 
                <button type="submit" name="dvd" formmethod="POST" formaction="/searchDvdById" value="searchDvdById"
                    class="btn btn-success">Search</button>
            </form><br/><br/>
        </c:if>
        <div class="table">
        <table align="center">
            <tr>
                <c:if test="${display == 'displayDvd'}">
                    <th colspan="9" class="title">DVD DETAILS</th>
                </c:if>
                <c:if test="${display == 'restoreDvd'}">
                    <th colspan="9" class="title">INACTIVE DVD DETAILS / TRASH DETAILS</th>
                </c:if>
                <c:if test="${display == 'purchaseDvd'}">
                    <th colspan="9" class="title">PURCHASE DVDS</th>
                </c:if>
            </tr>
            <tr>
                <th>Dvd Id</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Rating</th>
                <th>Language</th>
                <th>Release Date</th>
                <th>Category</th>
                <th>
                    <c:if test="${display == 'purchaseDvd'}">
                        select to purchase
                    </c:if>
                    <c:if test="${display != 'purchaseDvd'}">
                        Options
                   </c:if>
                </th>
            </tr>
            <c:forEach items="${dvds}" var="dvd">
                <tr>
                    <td><c:out value="${dvd.id}" /></td>
                    <td><c:out value="${dvd.name}" /></td>
                    <td><c:out value="Rs.${dvd.price}" /></td>
                    <td><c:out value="${dvd.quantity}" /></td>
                    <td><c:out value="${dvd.rating}" /></td>
                    <td><c:out value="${dvd.language}" /></td>
                    <td><c:out value="${dvd.releaseDate}" /></td>
                    <td>
                        <c:forEach items="${dvd.categories}" var="category">
                            <input type="hidden" value="${category.id}">
                            <c:out value="${category.name}"/>&nbsp;
                        </c:forEach>
                    </td>
                    <td>
                        <form> 
                            <input type="hidden" name="id" value="${dvd.id}">
                            <c:if test="${display == 'displayDvd'}">
                                <button type="submit" name="dvd"  formaction="/modifyDvd" formmethod="Post"
                                    value="updateDvd" class="btn btn-primary"> Edit </button>
                                <button type="submit" name="dvd" value="deleteDvd" formaction="/deleteDvd"
                                    onclick="return confirm('Are you sure want to delete?');"
                                    formmethod="Post" class="btn btn-danger"> Delete </button>
                            </c:if>
                            <c:if test="${display == 'restoreDvd'}">
                                <button type="submit" name="dvd" value="restoreDvdById" formaction="/restoreDvd"
                                    onclick="return confirm('Are you sure want to restore?');"
                                    formmethod="Post" class="btn btn-danger"> Restore </button>
                            </c:if>
                        </form>
                        <c:if test="${display == 'purchaseDvd'}">
                            <c:if test="${dvd.quantity != 0}">
                                <div class="pretty p-switch p-fill ">
                                    <input type="checkbox" name="check" value="${dvd.id}"/>
                                    <div class="state p-primary"><label></label></div>
                                </div>
                            </c:if>                             
                            <c:if test="${dvd.quantity == 0}">
                              <c:out value="out of stock" />
                            </c:if>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </div>
        <c:if test="${display == 'purchaseDvd'}">
            <input type="hidden" name="userid" value="${userid}" />
            <div class="purchase">
                <button type="button" onclick="isDvdSelected(event)" class="btn btn-info">Purchase</button>
                <div class="modal fade" id="deliveryAddress" role="dialog">
                <div class="modal-dialog">   
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Select Delivery Address</h4>
                    </div>
                    <div class="modal-body">
                        <div align="left">
                            <c:forEach var="address" items="${addresses}">
                                <label><input type="radio" name="addressId" value="${address.id}">
                                    ${address.street}, ${address.city}, ${address.state}, ${address.pincode}
                                </label><br/>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" name="customer"
                            value="placeOrder" formaction="/placeOrder" formmethod="Post" class="btn btn-success"
                            onclick="isAddressSelected(event)"> Place Order </button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">
                            Cancel</button>
                    </div>
                </div>
                </div>
                </div>

            </div>
        </c:if>
    </form>
</body>

<script type="text/javascript">
    
    function dvdResult() {

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

    function isDvdSelected(event) {
        var checked = false;
        var c = document.getElementsByTagName('input');
        for (var i = 0; i < c.length; i++) { 
            if (c[i].type == 'checkbox' && c[i].checked == true) { 
                checked = true; 
            }  
        }
        if (checked == false) {
            event.preventDefault();
            alert("select atleast one dvd to purchase..");
            return;
        }
        $("#deliveryAddress").modal("show"); 
    }

    function isAddressSelected(event) {
        var checked = false;
        var c = document.getElementsByTagName('input');
        for (var i = 0; i < c.length; i++) { 
            if (c[i].type == 'radio' && c[i].checked == true) { 
                checked = true; 
            }
        }
        if (checked == false) {
            event.preventDefault();
            alert("Select delivery address to place order...");
            return;
        }
    }

    function fnGoBack() {
         window.history.back();
    }

</script>





<html>
