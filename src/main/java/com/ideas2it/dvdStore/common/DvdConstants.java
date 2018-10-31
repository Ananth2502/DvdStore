package com.ideas2it.dvdStore.common;

/*
 * <p>
 * DvdConstatans class is have the entire output messages of java program..
 * If we want to change message in wherever in the program, just change here.. 
 * It will affect all the classes
 * 
 * @author Anantharaj
 *
 * </p>    
 */
public class DvdConstants {

    StringBuilder stringBuilder = new StringBuilder();

    public static final String LOGIN_SUCCESS = "Login success!!! \n";

    public static final String LOGIN_FAIL = "Login credentials mismatch...";

    public static final String MSG_ADMIN_LOGIN = "Enter Admin UserId : ";

    public static final String MSG_USER_CHOICE_MAIN = new StringBuilder("")
        .append(" 1.Dvd Menu\n 2.Category Menu\n 3.Customer Menu\n")
        .append(" 4.Exit ").toString();

    public static final String MSG_USER_CHOICE_DVD = new StringBuilder("")
        .append("Enter Your choice..\n 1.Add Dvd\n 2.Delete Dvd\n 3.Update Dvd")
        .append("\n 4.Display Dvd\n 5.Search Dvd by Name")
        .append("\n 6.Restore Dvd\n 7.Back ").toString();

    public static final String MSG_INVALID_CHOICE = " Invalid choice..\n ";

    public static final String MSG_INVALID_INPUT = " Invalid input..\n ";

    public static final String DATE_FORMAT = "uuuu/MM/dd";

    public static final String MSG_ADD_DVD = "Enter Dvd Details to add..";

    public static final String MSG_ADD_DVD_SUCCESS = " dvd added successfully ";

    public static final String MSG_DVD_ALREADY_EXISTS = 
        " Dvd details already exists..";

    public static final String MSG_DVD_ID_MISMATCH = 
        " Dvd ID mismatch..";

    public static final String MSG_ADD_MORE_DETAIL = 
        "Add another dvd details?  [y/n] :";

    public static final String MSG_ADD_DVD_FAIL = " dvd failed to insert ";

    public static final String MSG_DELETE_DVD = "Enter Dvd Id to delete : ";

    public static final String MSG_RESTORE_DVD = "Enter Dvd Id to restore : ";

    public static final String MSG_RESTORE_DVD_SUCCESS = 
        "Dvd restored successfully of Id : ";

    public static final String MSG_RESTORE_DVD_FAIL = 
        "failed to restore dvd of Id : ";

    public static final String MSG_DVD_EXISTS_IN_UPDATE = new 
        StringBuilder("This updated dvd details in inactive status, if you ")
        .append("want to please restore details").toString();

    public static final String MSG_CATEGORY_EXISTS_IN_UPDATE = new 
        StringBuilder("This updated categroy details in inactive status, ")
        .append("if you want to please restore details").toString();

    public static final String MSG_RESTORE_DVD_BY_NAME_PASS = 
        " dvd restored successfully";

    public static final String MSG_RESTORE_DVD_BY_NAME_FAIL = 
        " dvd failed to restore";

    public static final String MSG_CATEGORY_NOT_IN_TRASH = 
        "Category not in trash of Id :";

    public static final String MSG_DELETE_DVD_SUCCESS = 
        " dvd deleted successfully";

    public static final String MSG_DELETE_DVD_FAIL = 
        "dvd failed to delete... ";

    public static final String MSG_GET_DVD_FAIL = 
        "Dvd not in the dvd store of Id : ";

    public static final String MSG_DVD_NOT_IN_TRASH = 
        "Dvd not in the trash of dvd Id :";

    public static final String MSG_UPDATE_DVD = "Enter Dvd Id to update : ";

    public static final String MSG_USER_CHOICE_UPDATE = new StringBuilder("")
        .append("Choose Detail to update\n 1.name\t 2.Price\t 3.Quantity\t ")
        .append("4.Rating\t 5.Release date\t 6.Language\t ")
        .append("7.Category").toString();

    public static final String MSG_UPDATE_MORE_DETAIL = 
        "update more dvd details?  [y/n] :";

    public static final String MSG_UPDATE_DVD_SUCCESS = 
        " dvd updated successfully";

    public static final String MSG_UPDATE_DVD_FAIL = 
        " dvd failed to update ..";

    public static final String MSG_CANCEL_UPDATE = "Dvd update cancelled..";

    public static final String MSG_SERACH_DVD_BY_NAME = 
        "Enter the dvd Name to search : ";

    public static final String MSG_SEARCH_DVD_FAIL = " not in the dvd store..";

    public static final String MSG_SERACH_DVD_BY_CATEGORY = 
        "Enter the dvd category id to search : ";

    public static final String MSG_SERACH_BY_CATEGORY_FAIL = 
        " cannot match with any dvd in the dvd store..";

    public static final String MSG_DVD_EMPTY = "No dvd available in store.\n";

    public static final String MSG_GET_DVD_NAME = "Enter dvd name : ";

    public static final String MSG_DVD_NAME_INVALID = 
        "DVD Name cannot more than 30 letters.. ";

    public static final String MSG_GET_DVD_PRICE = "Enter dvd Price : ";

    public static final String MSG_DVD_PRICE_INVALID = 
        "Price cannot be nagative value... ";

    public static final String MSG_GET_DVD_QTY = "Enter quantity : ";

    public static final String MSG_DVD_QTY_INVALID = 
         "Quantity cannot be nagative value... ";

    public static final String MSG_GET_DVD_RATING = "Enter Rating : ";

    public static final String MSG_DVD_RATING_FAIL = 
        "Rating must be between zero to ten.. ";

    public static final String MSG_GET_DVD_RELEASE_DATE = 
        "Enter release date (dd/MM/yyyy) : ";

    public static final String MSG_DATE_FAIL = "Date format mismatch ";

    public static final String MSG_NOT_FUTURE_DATE = "Date cannot be future";

    public static final String MSG_LESS_DAY_AGO = 
        "Released Less than a day ago.. ";

    public static final String MSG_DAY_AGO = " day ago.. ";

    public static final String MSG_MONTH_AGO = " month ago.. ";

    public static final String MSG_YEAR_AGO = " year ago.. ";

    public static final String MSG_GET_DVD_LANGUAGE = "Enter Dvd Language :";

    public static final String MSG_DVD_LANGUAGE_INVALID = 
        " Dvd language cannot more than 30 letters.. ";

    public static final String MSG_GET_CATEGORY_ID = "Enter category Id : ";

    public static final String MSG_CATEGORY_ID_MISMATCH = 
        "Category Id mismatch or this is already selected..";

    public static final String MSG_GET_MOBILE_NUMBER = "Enter Mobile Number : ";

    public static final String MSG_ADD_MORE_CATEGORY = 
        "Add another category? [y/n]";

    public static final String MSG_GET_MORE_CATEGORY_ID = 
        " Add more category to this dvd...? [y/n] : ";

    public static final String MSG_CATEGORY_ID_INVALID = 
        " Category id from above list only.. choose another one";

    public static final String MSG_USER_CHOICE_CATEGORY = new 
        StringBuilder("Enter Your choice..\n 1.Add Category\n")
        .append(" 2.Delete Category\n 3.Display Category\n")
        .append(" 4.Search Dvd by Category\n 5.Update Category\n")
        .append(" 6.Restore Category\n 7.Back").toString();

    public static final String MSG_ADD_CATEGORY = "Enter Dvd Category to add";

    public static final String MSG_ADD_CATEGORY_SUCCESS = 
        " category added successfully ";

    public static final String MSG_CATEGORY_EXIXTS = 
        " Category already exists..";

    public static final String MSG_ADD_CATEGORY_FAIL = 
        " category failed to insert ";

    public static final String MSG_DELETE_CATEGORY = 
        " Enter category id to delete : ";

    public static final String MSG_DELETE_CATEGORY_SUCCESS = 
        " Dvd Category deleted successfully of category Id : ";

    public static final String MSG_GET_CATEGORY_FAIL = 
        " Dvd category not in the category list of category Id : ";

    public static final String MSG_CATEGORY_EMPTY = "Category list is empty.";

    public static final String MSG_UPDATE_CATEGORY = 
        " Enter category Id to update : ";

    public static final String MSG_RESTORE_CATEGORY = 
        " Enter Category Id to restore : ";

    public static final String MSG_RESTORE_CATEGORY_SUCCESS = 
        "category restored successfully for Id : ";

    public static final String MSG_RESTORE_CATEGORY_FAIL = 
        "failed to restore category for Id : ";

    public static final String MSG_RESTORE_CATEGORY_YN = new 
        StringBuilder("This updated category details in inactive status, ")
        .append("do you want to restore ? [y/n] :").toString();

    public static final String MSG_RESTORE_CATEGORY_BY_NAME_PASS = 
        " category restored successfully";

    public static final String MSG_RESTORE_CATEGORY_BY_NAME_FAIL = 
        " category failed to restore";

    public static final String MSG_UPDATE_CATEGORY_SUCCESS = 
        "Category updated successfully of Id : ";

    public static final String MSG_UPDATE_CATEGORY_FAIL = 
        "Failed to update category of category Id : ";

    public static final String MSG_GET_CATEGORY_NAME = "Enter category name : ";

    public static final String MSG_LOGIN_MOBILE_NUMBER = 
        "Enter Mobile number to login : ";

    public static final String MSG_USER_CHOICE_CUSTOMER = new 
        StringBuilder("Enter Your choice..\n 1.Customer login\n 2.Admin Login")
        .append("\n 3.Add Customer\n 4.Delete Customer\n 5.Search Customer")
        .append("\n 6.Restore Customer\n 7.Back ").toString();

    public static final String MSG_CHOICE_LOGIN_AS_USER = new 
        StringBuilder("Enter Your choice..\n 1.Update details\n 2.Purchase Dvd")
        .append("\n 3.Diaplay my orders \n 4.Cancel order\n 5.Back").toString();

    public static final String MSG_CHOICE_LOGIN_AS_ADMIN = new 
        StringBuilder("Enter Your choice..\n 1.Update Customer")
        .append("\n 2.Serach Customer By Mobilenumber\n 3.Display Customers")
        .append("\n 4.Display Inactive Customers\n 5.Display order by customer")
        .append("\n 6.Display All Orders \n 7.Back ").toString();

    public static final String MSG_GET_CUSTOMER_ID = "Enter customer Id : ";

    public static final String MSG_ADD_CUSTOMER = 
        "Enter the details to create an account...\n";

    public static final String MSG_DELETE_CUSTOMER = 
        "Enter the customer id to delete : \n";

    public static final String MSG_DELETE_CUSTOMER_SUCCESS = 
        "Customer deleted successfully for Id : ";

    public static final String MSG_DELETE_CUSTOMER_FAIL = 
        "failed to delete customer for Id : ";

    public static final String MSG_GET_CUSTOMER_FAIL = 
        "Customer details not available.. please enter valid customer id \n";

    public static final String MSG_RESTORE_CUSTOMER_SUCCESS = 
        "Customer account restored for the mobile number : " ;

    public static final String MSG_RESTORE_CUSTOMER_FAIL = 
       "failed to restore customer account for mobile number : ";

    public static final String MSG_CUSTOMER_NOT_IN_TRASH = 
        "Customer not in the trash of mobile number : ";

    public static final String MSG_RESTORE_CUSTOMER = 
        "Restore customer account based on Mobbile number..";

    public static final String MSG_CANCEL_ORDER_SUCCESS = 
        "Order has been successfully cancelled for Id :";

    public static final String MSG_CANCEL_ORDER_FAIL = 
        "Failed to cancell order for Id :";

    public static final String MSG_GET_CUSTOMER_NAME = "Enter name : ";

    public static final String MSG_GET_ADDRESS = "Enter Address : ";

    public static final String MSG_GET_ORDER_ID = "Enter order id :";

    public static final String MSG_CUSTOMER_ID_MISMATCH = 
        "Customer id mismatch..";

    public static final String MSG_CATEGORY_NAME_INVALID = 
        "Category Name cannot more than 30 letters..\n ";

    public static final String MSG_CUSTOMER_NAME_INVALID = 
        "Customer Name cannot more than 30 letters..\n ";

    public static final String MSG_MOBILE_NUMBER_INVALID = 
        "Mobile number must be numbers and cannot more than 15 letters..\n";

    public static final String MSG_SERACH_CUSTOMER_BY_MOBILE = 
        "Search customer details based on Mobile number...\n ";

    public static final String MSG_SERACH_CUSTOMER_BY_MOBILE_FAIL = 
        "No account match with this mobile number..";

    public static final String MSG_SERACH_CUSTOMER_BY_ID_FAIL = 
        "No account match with this ID..";

    public static final String MSG_ADD_CUSTOMER_SUCCESS = 
        " account created successfully.. ";

    public static final String MSG_ADD_CUSTOMER_FAIL = 
        " account failed to create..";

    public static final String MSG_USER_CHOICE_UPDATE_CUSTOMER = new 
        StringBuilder("Choose Detail to update\n 1.name\t 2.Mobile number ")
        .append("\t 3.Address ").toString();

    public static final String MSG_UPDATE_CUSTOMER = 
        "Enter customer id to update : ";

    public static final String MSG_UPDATE_CUSTOMER_SUCCESS = 
        "Update successfully completed for the customer : ";

    public static final String MSG_UPDATE_CUSTOMER_FAIL = 
        "Failed to update customer : ";

    public static final String MSG_CUSTOMER_ALREADY_EXISTS = 
        "Customer details already exists..";

    public static final String MSG_CUSTOMER_EXISTS_IN_UPDATE = 
        "Updated Customer details are in inactive status. If wants restore it";

    public static final String MSG_SELECT_DVD = 
        "Enter the dvd id to purchase : ";

    public static final String MSG_PURCHASE_MORE_DVD = 
        "Purchase more dvd...? [y/n] : ";

    public static final String MSG_ADD_ORDER_SUCCESS = 
        "Your order has been successfully placed...";

    public static final String MSG_ADD_ORDER_FAIL = 
        "Failed to purchasing dvd...";

    public static final String MSG_ORDER_ID_MISMATCH = 
        " Order ID mismatch..";


    public static final String MSG_GET_DVD_CATEGORIES = 
        " Enter Category Id from this list ";

    public static final String ERROR_INSERT_DVD = 
        "Something went wrong in insertng dvd :";

    public static final String ERROR_GET_DVD = 
        "Something went wrong in retrieving dvd : ";

    public static final String ERROR_GET_DVD_BY_CATEGORY = 
        "Something went wrong in retrieving dvd by category id : ";

    public static final String ERROR_GET_DVDS = 
        "Something went wrong in retrieving dvd details..";

    public static final String ERROR_DELETE_DVD = 
        "Something went wrong in deleting dvd id : ";

    public static final String ERROR_UPDATE_DVD = 
        "Something went wrong in updating dvd : ";

    public static final String ERROR_RESTORE_DVD = 
        "Something went wrong in restoring dvd : ";

    public static final String ERROR_RESTORE_ALL_DVDS = 
        "Something went wrong in restoring all dvd details..";

    public static final String ERROR_INSERT_CATEGORY = 
        "Something went wrong in inserting category : ";

    public static final String ERROR_GET_CATEGORY = 
        "Something went wrong in retrieving category : ";

    public static final String ERROR_GET_CATEGORIES = 
        "Something went wrong in retrieving categories..";

    public static final String ERROR_DELETE_CATEGORY = 
        "Something went wrong in deleting category : ";

    public static final String ERROR_UPDATE_CATEGORY = 
        "Something went wrong in updating catrgory : ";

    public static final String ERROR_RESTORE_CATEGORY = 
        "Something went wrong in restoring catrgory : ";

    public static final String ERROR_INSERT_CUSTOMER = 
        "Something went wrong in insertng customer :";

    public static final String ERROR_SERACH_CUSTOMER = 
        "Something went wrong in searching customer.. ";

    public static final String ERROR_DELETE_CUSTOMER = 
        "Something went wrong in deleting customer of Id : ";

    public static final String ERROR_UPDATE_CUSTOMER = 
        "Something went wrong in updating customer of Id : ";

    public static final String ERROR_RESTORE_CUSTOMER =
        "Something went wrong in restoring customer by mobile number : ";

    public static final String ERROR_GET_CUSTOMERS = 
        "Something went wrong in displaying customers...";

    public static final String ERROR_INSERT_ORDER = 
        "Something went wrong in placing an order...";

    public static final String ERROR_INSERT_ADDRESS = 
        "Something went wrong in inserting new address...";

    public static final String MSG_CUSTOMER_EMPTY = 
        "No customers available for store...";

    public static final String MSG_EMPTY_CUSTOMER_TRASH = 
        "No customers available in trash...";

    public static final String MSG_DVDS_IN_TRASH = 
        "List of dvds in trash.. choose to restore ";

    public static final String MSG_EMPTY_DVD_TRASH = 
        "No dvds in trash.. ";

    public static final String MSG_EMPTY_DVD_STORE = 
        "No dvds in store.. ";

    public static final String MSG_EMPTY_CATEGORY_TRASH = 
        "No categories in trash.. ";

    public static final String MSG_EMPTY_CATEGORY_STORE = 
        "No categories in store.. ";

    public static final String MSG_NO_ORDER_PLACED = 
        "No orders has been placed..";

    public static final String MSG_PRICE_ERROR = 
        "Price must be positive numbers...";

    public static final String MSG_QUANTITY_ERROR = 
        "Quantity must be numbers...";

    public static final String MSG_RATING_ERROR = 
        "Rating must be numbers...";

    public static final String MSG_LANGUAGE_ERROR = 
        "Language must be letters... ";

    public static final String MSG_SERVER_ERROR = "Server error..";

    public static final String MSG_TRY_AFTER = "  Try after Sometime..";

    public static final String LABEL_NAME = "Name = ";

    public static final String LABEL_PRICE = "price";

    public static final String LABEL_QTY = "quantity";

    public static final String LABEL_RATING = "rating";

    public static final String LABEL_RELEASE_DATE = "releaseDate";

    public static final String LABEL_RELEASED = "Released ";

    public static final String LABEL_CATEGORY_ID = "Category Id = ";

    public static final String LABEL_CATEGORY_NAME = " Category Name = ";

    public static final String LABEL_ID = "Id = ";

    public static final String LABEL_CATEGORY = "category";

    public static final String LABEL_LANGUANGE = "Language = ";

    public static final String LABEL_MOBILE = "Mobile No. = ";

    public static final String LABEL_MAIL = "Mail Id =";

    public static final String LABEL_ADDRESS = "Address = ";

    public static final String LABEL_SPACE = " ";

    public static final String LABEL_NEXTLINE = "\n";

    public static final Integer LABEL_MAX_RATING = 10;

    public static final Integer LABEL_NAMESPACE = 30;

    public static final Integer LABEL_MOBILENUMBER_LENGTH = 15;

    public static final String LABEL_DVDFILTER = "statusDvdFilter";

    public static final String LABEL_CATEGORYFILTER = "statusCatgoryFilter"; 

    public static final String LABEL_ORDERID = "Oreder Id = ";

    public static final String LABEL_TOTALPRICE = "Total Price = ";

    public static final String LABEL_ORDERDATE = "Order Date = ";

    public static final String LABEL_ORDEREDDVDS = "Ordered dvds : ";

    public static final String LABEL_CHECK = "check";

    public static final String LABEL_DVDS = "dvds";

    public static final String LABEL_DVD = "dvd";

    public static final String LABEL_DVDID = "dvdid";

    public static final String LABEL_DVDCONTROLLER_JSP = "DvdController.jsp";

    public static final String LABEL_DVDCREATION_JSP = "DvdCreation.jsp";

    public static final String LABEL_DVDDISPLAY_JSP = "DvdDisplay.jsp";

    public static final String LABEL_DVDSEARCH_JSP = "DvdSearch.jsp";

    public static final String LABEL_EMPTY = "empty";

    public static final String LABEL_ISEMPTY = "isEmpty";

    public static final String LABEL_EMPTYMESSAGE = "emptyMessage";

    public static final String LABEL_BUTTON = "button";

    public static final String LABEL_SAVE = "save";

    public static final String LABEL_UPDATE = "update";

    public static final String LABEL_ADDDVD = "addDvd";

    public static final String LABEL_DISPLAYDVD = "displayDvd";

    public static final String LABEL_RESTOREDVD = "restoreDvd";

    public static final String LABEL_SEARCHDVD = "searchDvd";

    public static final String LABEL_UPDATEDVD = "updateDvd";

    public static final String LABEL_DELETEDVD = "deleteDvd";

    public static final String LABEL_SEARCHDVDBYNAME = "searchDvdByName";

    public static final String LABEL_SEARCHDVDBYID = "searchDvdById";

    public static final String LABEL_RESTOREDVDBYID = "restoreDvdById";

    public static final String LABEL_CATEGORIES = "categories";

    public static final String LABEL_CATEGORYCREATION_JSP = 
        "CategoryCreation.jsp";

    public static final String LABEL_CATEGORYCONTROLLER_JSP = 
        "CategoryController.jsp";

    public static final String LABEL_CATEGORYDISPLAY_JSP ="CategoryDisplay.jsp";

    public static final String LABEL_DISPLAYCATEGORY = "displayCategory";

    public static final String LABEL_RESTORECATEGORY = "restoreCategory";

    public static final String LABEL_ADDCATEGORY = "addCategory";

    public static final String LABEL_DELETECATEGORY = "deleteCategory";

    public static final String LABEL_UPDATECATEGORY = "updateCategory";

    public static final String LABEL_RESTORECATEGORYBYID ="restoreCategoryById";

    public static final String LABEL_SEARCHDVDBYCATEGORY ="searchDvdByCategory";

    public static final String LABEL_LOGIN = "login";    

    public static final String LABEL_STREET = "street"; 

    public static final String LABEL_CITY = "city"; 

    public static final String LABEL_STATE = "state"; 

    public static final String LABEL_PINCODE = "pincode"; 

    public static final String LABEL_USERID = "userId";

    public static final String LABEL_PASSWORD = "password";

    public static final String LABEL_CUSTOMER = "CUSTOMER";

    public static final String LABEL_ADMIN = "ADMIN";

    public static final String LABEL_LOGIN_JSP ="Login.jsp";


    public static final String YES = "Y";

    public static final String NO = "N";

    public static final String STATUS = "status";

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String MOBILE = "mobileNumber";

    public static final String MAILID = "mailId";

    public static final String LANGUAGE = "language";

    public static final String RELEASE_DATE = "releaseDate";

    public static final String CUSTOMERID = "customerId";

    public static final String CUSTOMER = "customer";

    public static final String ADDRESS = "address";

    public static final String USERID = "userId";

    public static final String PASSWORD = "password";

    public static final String ROLE = "role";

    public static final String WELCOME = "Welcome ";

    public static final String DISPLAY = "display";

    public static final String MESSAGE = "message";

    public static final String SUCCESS = "success";

    public static final String FAIL = "fail";


}
