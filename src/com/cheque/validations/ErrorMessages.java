/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.validations;

/**
 *
 * @author Polypackaging-A1
 */
public class ErrorMessages {

    // --Error Messages list.
    //-------Validation Error messages---------
    //-------Validation Error messages---------
    public static final String purOrderQty
            = " Total Quantity \n cannot be less than ReOrder Level.";
    public static final String issueNoteQty
            = " Issue Note Quantity \n cannot be greater than Request Note Quantity.";
    public static final String GRNQty
            = " GRN Quantity cannot be greater than Request Note Quantity.";
    public static final String NoAccess = "No Access.";
    public static final String Default = "Default.";
    public static final String FingerPrintError
            = "Finger print not initalized properly.";
    public static final String InvalidRoomNo
            = "To preseed with registration card\n"
            + "all the dates should contain with a room no.";
    public static final String tableError
            = "Table must be filled with relevant Information.";
    public static final String MandatoryError
            = "The component marked in red \nmust be filled with valid data.";
    public static final String DayCountMismatch
            = "Reservation information missing some days.\nPlease generate missing days. ";
    public static final String idNotExsist
            = "The id values are not generated.";
    public static final String InvalidNic
            = "Please enter a valid NIC number.\nE.g.791524545V.";
    public static final String Error = "Error.";
    public static final String Empty = "Empty.";
    public static final String MandatoryEmpty
            = "Mandatory field cannot be empty.";
    public static final String InvalidCurrency
            = "Cheque can not add with currency type usd.";
    public static final String InvalidVoucherNo
            = "Invalid voucher Number.";
    public static final String MandatoryCustomer
            = "Customer ID field cannot be empty.";
    public static final String InvalidBalance
            = "Balance can not be greater than zero.";
    public static final String InvalidChque
            = "Please add a cheque number to the remark field.";
    public static final String InvalidCreditCard
            = "Please add the card number to the remark field.";
    public static final String InvalidReOrderLevel = "Invalid Re Order Level.";
    public static final String InvalidName = "Invalid name.";
    public static final String InvalidAdvance
            = "Payment can not exceed current advance amount.";
    public static final String InvalidAddress = "Invalid address.";
    public static final String InvalidTelephone
            = "Invalid telephone number.\nE.g.0112312312 or 0094112312312.";
    public static final String InvalidTelephoneOrDuplicate
            = "Enter valid unique telephone number like .\nE.g.0112312312 or 0094112312312 .";
    public static final String InvalidMobileOrDuplicate
            = "Enter valid unique mobile number like .\nE.g.0772312312 or 0094712312312.";
    public static final String InvalidMobile
            = "Invalid mobile number.\nE.g.0772312312 or 0094712312312.";
    public static final String InvalidFax
            = "Invalid fax number.\nE.g.0112312312 or 0094112312312.";
    public static final String InvalidFaxOrDuplicate
            = "Enter valid unique fax number like .\nE.g.0112312312 or 0094112312312.";
    public static final String ValidEntry = "Valid entry.";
    public static final String InvalidAgeGroup
            = "Invalid date of birth.\nAge cannot be less than 6 years.";
    public static final String InvalidEmailAddress = "Invalid email address.";
    public static final String InvalidEmailAddressOrDuplicate
            = "Enter valid and unique email address.";
    public static final String InvalidPAX = "PAX should be a number.";
    public static final String CustomerSuccesfullyCreated
            = "Customer profile created successfully.";
    public static final String CustomerSuccesfullyUpdated
            = "Customer profile updated successfully.";
    public static final String TravelAgentSuccesfullyCreated
            = "Travel Agent profile created successfully.";
    public static final String TravelAgentSuccesfullyUpdated
            = "Travel Agent profile updated successfully.";
    public static final String SuccesfullyUpdated = "Data updated successfully.";
    public static final String UserSuccesfullyUpdated = "User updated successfully.";
    public static final String SuccesfullyCreated = "Data created successfully.";
    public static final String SuccesfullyDeleted = "Data deleted successfully.";
    public static final String KOTBOTSuccesfullyCanceled = "Data Canceled successfully.";
    public static final String SuccesfullyRegistered
            = "Data registered successfully.";
    public static final String NotSuccesfullyUpdated
            = "Data not updated successfully.";
    public static final String UpdateUser
            = "Are you sure you want to update this user ?";
    public static final String NotSuccesfullyCreated
            = "Data not created successfully.";
    public static final String NotSuccesfullyDeleted
            = "Data not deleted successfully.";
    public static final String NotSuccesfullyReleased
            = "Room not successfully released.";
    public static final String SuccesfullyReleased
            = "Room successfully released.";
    public static final String NotSuccesfullyRegistered
            = "Data not registered successfully.";
    public static final String EmptyListView = "List cannot be empty.";
    public static final String InvalidNumber = "Invalid number.";
    public static final String InvalidAmount = "Invalid amount.";
    public static final String InvalidServiceCharge = "Invalid service charge.";
    public static final String InvalidText = "Invalid text.";
    public static final String InvalidEvent = "No event record found.";
    public static final String NoMenu = "No menu found for the event.";
    public static final String InvalidOutstanding
            = "Please settle outstanding before proceeding with this action.";
    public static final String InvalidId = "Invalid Id.";
    public static final String selectAUser = "You have not Selected a User";
    public static final String EventAlreadyExist
            = "An event is already booked for this date.";
    public static final String InvalidDate = "Invalid date.";
    public static final String InvalidDescription = "Invalid description.";
    public static final String InvalidQty = "Quantity should be a number\nand grater than zero";
    public static final String InvalidHallPlan = "Hall plan not selected.";
    public static final String InvalidVat = "Invalid Vat.";
    public static final String Update
            = "Are you sure you need to update this record ?.";
    public static final String Delete
            = "Are you sure you need to Delete this record ?.";

    public static final String Cancel
            = "Are you sure you need to Cancel this record ?.";
    public static final String AlreadyExist = "Already exist.";
    public static final String DuplicateFound = "Duplicate item cannot exist.";
    public static final String DateLessThanToday
            = "Date can not be less than todays date.";
    public static final String DateGraterThanToday
            = "Date can not be greater than todays date.";
    public static final String ConnectErrorYahoo
            = "Unable to connect to Yahoo Finance!.";
    public static final String InvalidPrice = "Invalid Price.";
    public static final String InvalidPercentage = "Invalid Percentage.";
    public static final String AllreadyExist = "Data already exist.";
    public static final String InvalidPaxQty = "Invalid Pax Quantity.";
    public static final String InvalidMargin = "Invalid Margin.";
    public static final String InvalidRecord
            = "Please select a valid record before proceed with approve.";
    public static final String RecordApproved = "Record Approved Successfully.";
    public static final String RecordNotApproved
            = "Record not approved due to record alternation.";
    public static final String InvalidUnit = "Invalid unit.";
    public static final String InvalidItemName = "Item name already exist.";
    public static final String LateCheckout
            = "Customer is late please add late checkout charges.";
    public static final String EarlyCheckin
            = "Customer is early please add early checkin charges.";
    public static final String SummaryIdExist
            = "Summary Id you enterd already exist.";
    public static final String SummaryIdNotExist
            = "Summary Id you enterd not exist in the system.";
    public static final String TransportIdNotExist
            = "Transport Id you enterd not exist in the system.";
    public static final String TransportIdExist
            = "Transport Id you enterd already exist in the system.";
    public static final String SelectElement
            = "Select at leas 2 checkboxes for half boad.";
    public static final String PrintProcess = "Printing in process.";
    public static final String ItemsOutOfStock = "Are out of stock.";
    public static final String ItemOutOfStock = "is out of stock.";
    public static final String ItemNotExist
            = "Item is not existing.Add this Item to the Stock";
    public static final String AccessDenied = "Access Denied.";
    public static final String NoItemFound = "No item found in the item list.";
    public static final String ToDateOverLap
            = "To date can not be less than From date.";
    public static final String FromDateOverLap
            = "From date can not be greater than To date.";
    public static final String AdvanceIdNotExist
            = "Advance Id Not Exist In The System. Please select a Valid ID.";
    public static final String HighUsedQty
            = "Used Quantity can not be greater than available quantity.";
    public static final String InvalidCheque
            = "Can’t add cheque payment for currency type USD.";

    public static final String HighUsedAmount
            = "Payment can not be greater than amount.";
    //-------------Sundry Bill Error messages----
    public static final String InvalidRate = "Invalid Rate.";

    //--Room Rate Registration Error messages----
    public static final String RateCodeExist
            = "Rate code is already exist in the system.";

    //-------BOT Error messages------------------
    public static final String NoBOT = "There is no BOT Record to delete.";

    //-------KOT Error messages------------------
    public static final String NoKOT = "There is no KOT Record to delete.";
    public static final String NoItemesSelected = "No Item Selected.";
    public static String InvalidCancelQty
            = "Cancel quantity can not\nexceed order quantity and\n "
            + "can not be less than or equal to Zero";

    //-------Room registration Error messages----
    public static final String InvalidRoomNumber = "Invalid Room Number.";
    public static final String InvalidFloorNumber = "Invalid Floor Number.";
    public static final String OverlapNumber
            = "To room number should be greater than From room no.";
    public static final String InvalidLocation = "Invalid Location.";
    public static final String RoomSuccesfullyRegistered
            = "Room Succesfully Registered.";
    public static final String RoomSuccesfullyUpdated
            = "Room Succesfully Updated.";
    public static final String RoomSuccesfullyDeleted
            = "Room Succesfully Deleted.";
    public static final String RoomAlreadyExist
            = "Room already exist in the system.";
    public static final String InvalidRoomType = "Invalid Room Type.";

    //-------Input output Error messages---------
    public static final String ComponentNotInitialized
            = "Component not initialized.";
    public static final String IncompleteData = "Incomplete data.";
    public static final String InvalidDataInFile
            = "Error while scanning system file.";
    public static final String SystemFileNotFound = "Data-stream not found.";

    //-------DB connection Error messages---------
    public static final String DbConnectionFail
            = "Database connection failed.\nContact system administrator.";
    public static final String IdNotGenerated
            = "ID not generated. /n please check and restart application.";
    public static final String UnsavedData = "Data not saved.";

    //--Master bill connection Error messages----
    public static final String NoKotBot
            = "No KOT or BOT available for the event.";
    public static final String MasterBillExist
            = "Master bill already added for the event.";
    public static final String RoomMasterBillExist
            = "Master bill already exist. ";
    public static final String RoomMasterIDNotExist
            = "Master bill does not exist. ";
    public static final String InvalidSettlement
            = "Can't Settle the bill that have payment to proceed with.";

    //--Resevation connection Error messages-----
    public static final String SuccesfullyCanceled
            = "Reservation cancel successfully.";
    public static final String NotSuccesfullyCanceled
            = "Reservation not cancel successfully.";
    public static final String DepatureDateOverLap
            = "Depature must be a future date.";
    public static final String ArrivalDateOverLap
            = "Arrival must be before departure.";
    public static final String InvalidCheckinType = "Invalid check-in type.";

    //------Hall Registration-----------------------------
    public static final String HallSuccesfullyDeleted
            = "Hall succesfully deleted.";

    //-------Login-------------------
    public static String InvalidUsername = "Invalid Username.";
    public static String PasswordsMismatch = "Password Mistmatch.";

    //Restuarent Event Bill----------------
    public static String EventBillSuccesfullyCreated
            = "Event Bill Succesfully Created.";
    public static String InvalidPayment
            = "Invalid Payment.\nPayment can not be less or greater than balance.";
    public static String InvalidPaymentLow
            = "Invalid Payment.\nPayment can not be less than Zero.";
    public static String InvalidPaymentHigh
            = "Invalid Payment.\nPayment can not be greater than balance.";
    public static String InvalidDiscount = "Invalid Discount.";
    public static String InvalidTotal = "Invalid Total.";
    public static String InvaliCurrencyTypeForCheck
            = "Invalid Currency type for cheque.";

    //Titles for infromation dialogs-----------------------
    public static String ItemsOutOfStockList
            = "The items listed below are not sufficient to process the request.";

    //Account----------------
    public static String InvalidAccNumber = "Invalid Account Number.";
    public static String InvalidAccName = "Invalid Account Name.";
    public static String InvalidOpeningBal = "Invalid Opening Balance.";
    public static String InvalidTaxCode = "Invalid Tax Code.";
    public static String InvalidPurNo = "Invalid Purchase No.";
    public static String InvalidSupInvNo = "Invalid Supplier Invoice No.";
    public static String InvalidMemo = "Invalid Memo.";
    public static String DeviceNotInitialized = "Device not initialized.";

    //Group Reservation------------------------------------
    public static String InvalidAGrpID = "Invalid Group Number.";
    public static String InvalidSummaryId = "Invalid Summary ID.";
    public static String InvalidAgentId = "Invalid Agent ID.";
    public static String InvalidAgentName = "Invalid Agent Name.";
    public static String InvalidTaxRateCode = "Invalid Tax Rate Code.";
    public static String InvalidTaxRatePrefix = "Invalid Prifix.";
    public static String emptyRoomNo = "Select a Room No.";
    public static String InvalidallocRoom = "Allocate Room Qunatity should be same to Pax Quantity";

    public static String ReportNotAssigned = "Report Not Assigned.";
    public static String selectSuplier = "Please select a Supplier before selecting an Item";
    public static String PaxError = "Invalid Pax.";
    public static String ItemNotCancelled = "No Cancel item found.";
    public static String InvalidPaxCount = "Please select a pax amount";
    public static String performerInvoiceSavedSuccessfully = "Performer Invoice Report Saved Successfully.";

    //Capital Allowance
    public static String InvalidAllowanceId = "Invalid Allowance ID.";
    public static String InvalidTitle = "Invalid Title.";

    //Gerneral Ledger Account Registration
    public static String InvalidLedgerAccountId = "Invalid Ledger Account ID";
    public static String InvalidRemark = "Invalid Remark";

    public static String NoRateFoundSelectedBasis = "No Room charge found for some selected room basis";
    public static String CannotDeleteAfterRegistration = "Cannot Delete Group Reservation After Registration";
    public static String CannotCancelAfterReservation = "Cannot Cancel Group Reservation After Reservation";
    public static String CannotDeleteSummary = "Cannot delete reservation that has room master bill";
    public static String BranchNotAssigned = "Branch Not Assigned.";
    public static String CannotDeleteDueToDependancy = "data cannot delete, First delete all the used locations of this record.";

    public static String BedAndBreakfastError = "Meal rate can only set for Breakfast.";
    public static String HalfBoardError = "Need to set meal rate for any two meals.";
    public static String FullBoardError = "All the three meals need to have a meal rates.";
    public static String RoomOnlyError = "No meal rates applies for any meal.";

    public static String InvalidImageSize = "Image Size is too large.";
    public static String InvalidImage = "Upload an Image";
    
    public static final String updateInBackground = "Update running in background.";
    public static final String updateApplication = "New Update available.Do you wish to proceed.";
    public static final String uptoDate = "Applcation is upto date.";
    public static final String newUpdateFound = "New update found.";
    
    public static final String InvalidAccNo = "Account number already exist.";
    public static final String MACFail = "License failed. Please re-enter license credentials again.";

}
