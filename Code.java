import java.util.*;
import java.io.*;

// *! CHECK IF THESE ARE NECESSARY IMPORT, BUT ANYWAYS REMEMBER THEM
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Code {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
 //-----------------------------The code here reads the already existing product file if any to input the products so that user don't have to put the products again and again.
        InventoryManagement im = new InventoryManagement();
 // ---------------------------------------------------------------------------------------------------------------------------



//----------------------------------------------------- FIRST PAGE--------------------------------------------------------------------------------------------------------------------------
        while (true) {
            // BASIC INTERFACE
            System.out.println("\nWelcome to my project.");
            System.out.println("\nWhat do you want to do?\n");
            System.out.println("1. Add/Update the product details of your store.(Enter 1 on your keyboard.)\n2. Make a bill for the customer.(Enter 2 on your keyboard.)\n3. To exit the program.(Enter 3 on your keyboard.)");

            // *NOW LET'S DO INPUT VALIDATION AND ERROR CASE HANDLING
            // !Aise Initialise karega to local variable ka issue nahi aayega
            int basicCase = 0;
            // !YAHA PE WHILE LAGANA PADEGA KYUKI VARNA BREAK SE LOOP EXIT AAYEGA AND IT WILL BE SAME EVERYWHERE
            while (true) {
                if (sc.hasNextInt()) {
                    basicCase = sc.nextInt();
                    sc.nextLine();
                    if (basicCase == 1 || basicCase == 2 || basicCase == 3) {
                        break;
                    } else {
                        System.out.println("Invalid Input");
                    }
                } else {
                    System.out.println("Invalid Input");
                    sc.nextLine();
                }
            }

            if(basicCase == 3) {
                System.out.println("Thank you for using our program.");
                break;
            }

//--------------------------------------------------------------------------------------------------------------------------------------------
            // SWITCH CASE FOR BASIC INTERFACE

            while (true) {
                switch (basicCase) {
                    case 1:                                                   //PRODUCT ADDING INTERFACE
                        while (true) {

                            System.out.println("\nFollowing is the layout of your inventory.\n");
                            im.showInventory(im.getList());
                            
                            System.out.println("1. For adding a new product press 1 on your keyboard.\n2. For updating the informaation of a product press 2 on your keyboard.\n3. To get to previous window.\n");

                            //PRODUCT ADDITION SWITCH CASE
                            int ProductCase = 0;
                            while (true) {
                                if (sc.hasNextInt()) {
                                    ProductCase = sc.nextInt();
                                    sc.nextLine();
                                    if (ProductCase == 1 || ProductCase == 2 || ProductCase == 3) {
                                        break;
                                    } else {
                                        System.out.println("Invalid Input");
                                    }
                                } else {
                                    System.out.println("Invalid Input");
                                    sc.nextLine();
                                }
                            }


                            if (ProductCase == 3) {
                                break;
                            }

                            switch (ProductCase) {

                                case 1:
                                    int existingSize = im.getList().size();//Adding a new product
                                    String productName;
                                    System.out.print("Enter Product name: ");
                                    while(true) {
                                        productName = sc.nextLine().trim();// Remove extra spaces before and trailing
                                        if(productName.isEmpty()) {
                                            System.out.println("Cannot have an empty name.");
                                        } else {
                                            break;
                                        }
                                    }

                                    System.out.print("\nEnter Product price: ");
                                    int productPrice = 0;
                                    // * ERROR CASE FOR PRICE
                                    while (true) {
                                        if (sc.hasNextInt()) {
                                            productPrice = sc.nextInt();
                                            sc.nextLine();
                                            if (productPrice >= 0) {
                                                break;
                                            } else {
                                                System.out.println("Incorrect Input");
                                            }
                                        } else {
                                            System.out.println("Incorrect Input");
                                            sc.nextLine();
                                        }
                                    }


                                    // * SAME FOR PRODUCT AMOUNT
                                    System.out.print("\nEnter Product quantity: ");
                                    int productAmount = 0;
                                    while (true) {
                                        if (sc.hasNextInt()) {
                                            productAmount = sc.nextInt();
                                            sc.nextLine();
                                            if (productAmount >= 0) {
                                                break;
                                            } else {
                                                System.out.println("Incorrect Input");
                                            }
                                        } else {
                                            System.out.println("Incorrect Input");
                                            sc.nextLine();
                                        }
                                    }

                                    ProductDetails p1 = new ProductDetails(productName, productPrice, productAmount);
                                    im.getList().add(p1);

                                    im.addList(im.getList(), existingSize);
                                    break;


                                case 2:                                 //UPDATING A PRODUCT
                                    System.out.println("\nEnter the number of product(displayed above) to make changes in your inventory: \n");

                                    // * WE HAVE TO PUT ERROR CASE HERE ALSO AS IT WILL SHOW A PROBLEM IF WE PUT THE NUMBER GREATER AND SMALLER THAN THE LIST AND THE GENERAL INPUT CORRECTION HERE ALSO
                                    int numProduct = 0;
                                    while (true) {
                                        if (sc.hasNextInt()) {
                                            numProduct = sc.nextInt();
                                            sc.nextLine();
                                            if (numProduct > 0 && numProduct <= im.getList().size()) {
                                                break;
                                            } else {
                                                System.out.println("Incorrect Input");
                                            }
                                        } else {
                                            System.out.println("Incorrect Input");
                                            sc.nextLine();
                                        }
                                    }


                                    System.out.println("1.To delete " + im.getList().get(numProduct - 1).name + " from your inventory press 1.\n2.To update the product press 2.");

                                    // FOR UPDATE SWITCH CASE
                                    int updateCase = 0;
                                    while (true) {
                                        if (sc.hasNextInt()) {
                                            updateCase = sc.nextInt();
                                            sc.nextLine();
                                            if (updateCase == 1 || updateCase == 2) {
                                                break;
                                            } else {
                                                System.out.println("Invalid Input");
                                            }
                                        } else {
                                            System.out.println("Invalid Input");
                                            sc.nextLine();
                                        }

                                    }

                                    switch (updateCase) {
                                        case 1:                               //DELETING THE PRODUCT
                                            im.getList().remove(numProduct - 1);
                                            System.out.println("The product has been deleted from your inventory.");
                                      

                                            //** NOW WE OVERWRITE THE WHOLE FILE AND LOOP THE WHOLE LIST AGAIN AND AS THE DELETE PART IS ALREADY REMOVED IT WILL NOT WRITE THAT PART */
                                            im.overwriteInventory(im.getList());
                                            // ** break the code after making changes in the csv file.
                                            break;

                                        case 2:                               //UPDATING THE PRODUCT
                                            System.out.print("Enter Product name: ");
                                            String upProductName;
                                            while(true) {
                                                upProductName = sc.nextLine().trim();
                                                if(upProductName.isEmpty()) {
                                                    System.out.println("Cannot have an empty name.");
                                                } else {
                                                    break;
                                                }
                                            }

                                            System.out.print("\nEnter Product price: ");
                                            int upProductPrice = 0;
                                            while (true) {
                                                if (sc.hasNextInt()) {
                                                    upProductPrice = sc.nextInt();
                                                    sc.nextLine();
                                                    if (upProductPrice >= 0) {
                                                        break;
                                                    } else {
                                                        System.out.println("Incorrect Input");
                                                    }
                                                } else {
                                                    System.out.println("Incorrect Input");
                                                    sc.nextLine();
                                                }
                                            }


                                            System.out.print("\nEnter Product quantity: ");
                                            int upProductAmount;
                                            while (true) {
                                                if (sc.hasNextInt()) {
                                                    upProductAmount = sc.nextInt();
                                                    sc.nextLine();
                                                    if (upProductAmount >= 0) {
                                                        break;
                                                    } else {
                                                        System.out.println("Incorrect Input");
                                                    }
                                                } else {
                                                    System.out.println("Incorrect Input");
                                                    sc.nextLine();
                                                }
                                            }

                                            im.getList().get(numProduct - 1).setProductInfo(upProductName, upProductPrice, upProductAmount);
                                            System.out.println("The product details have been updated.");

                                            im.overwriteInventory(im.getList());
                                            // ** FOR UPDATING WE WILL DO THE SAME AS WE DID FOR DELETING, JUST OVERWRITE THE WHOLE THING


                                            break;

                                        default:
                                            System.out.println("Enter correct input.");
                                    }
                                    break;
                            }

                        }
                        break;


                    case 2:                                                     // CUSTOMER INFORMATION INPUT


                        System.out.print("\nEnter customer's name: ");
                        String customerName;
                        while(true) {
                            customerName = sc.nextLine().trim();
                            if(customerName.isEmpty()) {
                                System.out.println("Cannot have an empty name.");
                            } else {
                                break;
                            }
                        }

                        String customerPhoneNumber;
                        while (true) {
                            System.out.print("\nEnter customer's phone number: ");
                            customerPhoneNumber = sc.nextLine();

                            if (customerPhoneNumber.length() == 10 && customerPhoneNumber.charAt(0) != '0' && customerPhoneNumber.matches("[0-9]+")) {//* ALSO ADD THIS TO YOUR NOTES */) {
                                break;
                            } else {
                                System.out.println("Enter correct phone number.");
                            }
                        }

                        System.out.print("\nEnter customer's address: ");
                        String customerAddress;
                        while(true) {
                            customerAddress = sc.nextLine().trim();
                            if(customerAddress.isEmpty()) {
                                System.out.println("Cannot have an empty address name.");
                            } else {
                                break;
                            }
                        }
                        UserInfo customerInfo = new UserInfo(customerName, customerPhoneNumber, customerAddress);

                        ArrayList<Bill> billList = new ArrayList<>();

                        while (true) {

                            System.out.println("\n1. Enter the products the customer has bought.\n2. Make the bill.\n3. Exit to the previous windows.");
                            int billCase = 0;

                            while (true) {
                                if (sc.hasNextInt()) {
                                    billCase = sc.nextInt();
                                    sc.nextLine();
                                    if (billCase == 1 || billCase == 2 || billCase == 3) {
                                        break;
                                    } else {
                                        System.out.println("Invalid Input");
                                    }
                                } else {
                                    System.out.println("Invalid Input");
                                    sc.nextLine();
                                }
                            }

                            if (billCase == 3) {
                                break;
                            }

                            switch (billCase) {
                                case 1:
                                    while (true) {
                                        System.out.println("\nFollowing is the layout of your inventory.\n");

                                        im.showInventory(im.getList());

                                        System.out.println("\nEnter the product no. the customer has chosen from above list: ");
                                        int customerNum;

                                        while (true) {
                                            if (sc.hasNextInt()) {
                                                customerNum = sc.nextInt();
                                                sc.nextLine();
                                                if (customerNum > 0 && customerNum <= im.getList().size()) {
                                                    if(im.getList().get(customerNum-1).amount == 0) {
                                                        System.out.println("The product is out of stock.");
                                                    } else {
                                                        break;
                                                    }
                                                } else {
                                                    System.out.println("Incorrect Input");
                                                }
                                            } else {
                                                System.out.println("Incorrect Input");
                                                sc.nextLine();
                                            }
                                        }

                                        int n = customerNum - 1;
                                        System.out.println("Enter the quantity taken of " + im.getList().get(customerNum - 1).name + ": ");
                                        int customerAmount;
                                        while (true) {
                                            if (sc.hasNextInt()) {
                                                customerAmount = sc.nextInt();
                                                sc.nextLine();

                                                //* EXTRA IF ISLIYE DALA HAI AS IT WILL BE READ NONTHELESS AND ELSE STATEMENT WILL BE CONNECTED TO THE LAST IF ANYWAYS */
                                                if (customerAmount > im.getList().get(n).amount) {
                                                    System.out.println("The given quantity exceeds the storage quantity.");
                                                }
                                                if (customerAmount > 0 && customerAmount < im.getList().get(n).amount) {
                                                    break;
                                                } else {
                                                    System.out.println("Invalid Input");
                                                }
                                            } else {
                                                System.out.println("Invalid Input");
                                                sc.nextLine();
                                            }
                                        }


                                        // ** NOW WE WILL PUT ITEMS IN BILL LIST FOR THAT WE HAVE TO CREATE A NEW OBJECT FOR BILL BUT FOR THAT WE NEED THINGS
                                        String billname = im.getList().get(n).name;
                                        int billprice = im.getList().get(n).price;
                                        int billqty = customerAmount;

                                        // ** NOW WE WILL MAKE CHANGES IN THE AMOUNT OF THE PRODUCT LIST AS WE HAVE CHANGED THE AMOUNT OF THE PRODUCT WE BOUGHT
                                        // ! Remember to make this change in the main csv file also
                                        im.getList().get(n).amount -= customerAmount;

                                        int subtotal = billprice * billqty;
                                        Bill b = new Bill(billname, billprice, billqty, subtotal);
                                        billList.add(b);


                                        System.out.println("To exit out press 0 otherwise press any integer on your keyboard.");
                                        int exitBill;
                                        while (true) {
                                            if (sc.hasNextInt()) {
                                                exitBill = sc.nextInt();
                                                sc.nextLine();
                                                break;
                                            } else {
                                                System.out.println("Invalid Input");
                                                sc.nextLine();
                                            }
                                        }

                                        if (exitBill == 0) {
                                            break;
                                        }
                                    }
                                    break;


                                case 2:
                                    System.out.println("Total Bill is: ");
                                    int totalAmount = 0;
                                    for (int i = 0; i < billList.size(); i++) {
                                        totalAmount += billList.get(i).subtotal;
                                    }
                                    System.out.println(totalAmount);


                                    im.overwriteInventory(im.getList());


                                    // !VERY IMPORTANT TO LEARN A NEW THING
                                    LocalDateTime now = LocalDateTime.now();
                                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

                                    // *formatted contains colons (:) which are invalid in filenames on Windows. So clean them
                                    String formatted = now.format(format);

                                    // * NOW WE WILL MAKE THE ACTUAL BILL

                                    String billFile = "Bill" + formatted + "_" + customerName + ".csv";

                                    try (FileWriter bill = new FileWriter(billFile)) {
                                        bill.append("              XYZ Store").append("\n");
                                        bill.append("          " + formatted).append("\n");
                                        bill.append("S.no.,Product,Price,Qty,Subtotal");
                                        bill.append("\n");


                                        // * NOW ON ACTUAL BILL
                                        for (int i = 0; i < billList.size(); i++) {
                                            bill.append(i + 1 + "," + billList.get(i).name + "," + billList.get(i).price + "," + billList.get(i).quantity + "," + billList.get(i).subtotal);
                                            bill.append("\n");
                                        }

                                        bill.append("Total Amount:                 ");

                                        // !REMEMBER APPEND DOES NOT TAKE INTEGER ONLY STING IT TAKES
                                        bill.append(String.valueOf(totalAmount));
                                        bill.append("\n");

                                    } catch (Exception e) {
                                        // TODO: handle exception
                                    }

                            }


                        }


                    default:
                        System.out.println("Enter correct input.");
                        break;


                }

                break;
            }

        }


    }
}


class UserInfo {
    String name;
    String phoneNumber;
    String address;

    UserInfo(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }


}

class ProductDetails {
    String name;
    int price;
    int amount;

    ProductDetails(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setProductInfo(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}


class Bill {
    String name;
    int price;
    int quantity;
    int subtotal;

    Bill(String name, int price, int quantity, int subtotal) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }
}

class InventoryManagement {
    private ArrayList<ProductDetails> list;
    private final String csvFile = "Product.csv";

    public InventoryManagement() {
        list = new ArrayList<>();
        loadInventory(list);
    }

    public ArrayList<ProductDetails> getList() { // *This method returns a list, and the items inside it are Product objects.This method will return a list that contains objects of the Product class" */
        return list;
    }
    
    // ?Static methods can’t access instance (non-static) variables like productList.
    public void loadInventory(ArrayList<ProductDetails> list) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            reader.readLine();
            reader.readLine();
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");
                if (parts.length < 4) {
                    System.out.println("Skipping the csv line: " + line);
                    continue;
                }
                String name = parts[1];
                int price = Integer.parseInt(parts[2]);
                int qty = Integer.parseInt(parts[3]);

                ProductDetails p = new ProductDetails(name, price, qty);
                list.add(p);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void addList(ArrayList<ProductDetails> list, int existingSize) {
        try (FileWriter mainfile = new FileWriter(csvFile, true)) {
            // *! CREATING FILE CLASS IT DOES NOT CREATE A NEW FILE IT JUST A NEW OBJECT
            // WHICH DIRECT TO THE FILE AND HELP INTERACTING FILE.
            File f = new File("Product.csv");
            boolean fileLength = f.length() == 0;

            if (fileLength) {
                mainfile.append("Store Name").append("\n");
                mainfile.append("Product details: ").append("\n");
                mainfile.append("S.no.,Name,Price,Qty.");
                mainfile.append("\n");

            }

            // ** HERE WE WILL ADD NEW PRODUCT IN THE FILE AND LIST
            for (int i = existingSize; i < list.size(); i++) {
                mainfile.append(i + 1 + "," + list.get(i).name + "," + list.get(i).price + "," + list.get(i).amount);
                mainfile.append("\n");
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void showInventory(ArrayList<ProductDetails> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ".Product name: " + list.get(i).name);
            System.out.println("  Product price: " + list.get(i).price);
            System.out.println("  Product amount: " + list.get(i).amount);
            System.out.println();
        }
    }


    public void overwriteInventory(ArrayList<ProductDetails> list) {
        try (FileWriter mainfile = new FileWriter(csvFile, false)) {
            // *! What false does:
            // *! Tells Java: “overwrite the file”
            // *! Clears all existing contents inside the file
            // *! Keeps the same file, just starts writing from the top again
            mainfile.append("Store Name").append("\n");
            mainfile.append("Product details: ").append("\n");
            mainfile.append("S.no.,Name,Price,Qty.");
            mainfile.append("\n");


            for (int i = 0; i < list.size(); i++) {
                mainfile.append(i + 1 + "," + list.get(i).name + "," + list.get(i).price + "," + list.get(i).amount);
                mainfile.append("\n");
            }


        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
