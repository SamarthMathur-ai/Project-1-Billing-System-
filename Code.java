import java.util.*;
import java.io.*;


public class Code {

        public static void main(String[] args) {
                UserInterface user = new UserInterface();
                Extra ex = new Extra();
                Product pro1 = new Product("Product 1", 10);
                Product pro2 = new Product("Product 2", 20);
                File billfile = new File("Bill.txt");


                System.out.println("Welcome to our website!!\n\nJust follow the two step process to buy products at exciting offer.\n");
                Scanner sc = new Scanner(System.in);

                 //FOR USERNAME
                System.out.print("Enter your name: ");
                String name = sc.nextLine();
                
                user.setUsername(name);
                System.out.println();

                //FOR PASSWORD
                System.out.print("Enter your phoneNumber: ");
                String phoneNumber = sc.next();
                user.setPhoneNumber(phoneNumber);
                System.out.println();

                //PRODUCT CHOOSING

                

                int totalAmount = 0;
                Product totalprices[] = {pro1, pro2};
               
                

                //TOTAL AMOUNT CALCULATION
                
                do {
                        System.out.println("Hello!! " + name + " here are the following products you can select from: ");
                        System.out.println("Product 1 : 10 ruppees(Enter 1)\nProduct 2 : 20 ruppees(Enter 2)\nTotal Bill(Enter 3)");

                        int number = sc.nextInt();

                        switch (number) {
                                case 1: pro1.totalprice = 0;
                                        System.out.println("Enter the amount you want to have: ");
                                        pro1.amount = sc.nextInt();
                                        pro1.totalprice += pro1.calculatingTotalAmount(pro1.amount, pro1.price);     
                                        break;

                                case 2: pro2.totalprice = 0;
                                        System.out.println("Enter the amount you want to have: ");
                                        pro2.amount = sc.nextInt();
                                        pro2.totalprice += pro1.calculatingTotalAmount(pro2.amount, pro2.price);
                                        break;

                                case 3: try {
                                                billfile.createNewFile();
                                        } catch (IOException e) {
                                        
                                                e.printStackTrace();
                                        }

                                        

                                        System.out.println("\nYour Bill: ");
                                        for(Product p : totalprices) {
                                        totalAmount += p.totalprice;
                                        System.out.println(p.name + " : " + p.price + " Ruppees, Total amount purchased: " + p.amount);
                                       
                                        }
                                        System.out.println("Total price of the purchase is: " + totalAmount);
                                        
                                        try {
                                                FileWriter filewriter = new FileWriter("Bill.txt");
                                                
                                                filewriter.write("Your Bill: " + "\n");
                                                for(Product p : totalprices) {
                                                        filewriter.write(p.name + " : " + p.price + " Ruppees, Total amount purchased: " + p.amount + "\n");;
                                                }
                                                filewriter.write("Total price of the purchase is: " + totalAmount);
                                                filewriter.close();

                                        } catch (IOException e) {
                                                
                                                e.printStackTrace();
                                        }
                                        

                                        
                                        
                                

                                        return;                                                                             
                                default: System.out.println("Enter valid input.");
                                        break;           
                        }
                        System.out.println();
                }while(true);
                


               

                
        }
}


class Product {
        
        int totalprice;
        int price;
        int amount;
        String name;

        Product(String name, int price) {
                this.name = name;
                this.price = price;
        }
        
       
        public void setAmount(int amount) {
                this.amount = amount;
        }

        public int getAmount() {
                return this.amount;
        }

        

        public int calculatingTotalAmount(int amount, int price) {
                setAmount(amount);
                return amount*price;
        }
}



class UserInterface {
        String username;
        String phoneNumber;
        public void setUsername(String username) {
               this.username = username;
        }
        public String getUsername() {
                return username;
        }
        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }
        public String getPhoneNumber() {
                return phoneNumber;
        }

}

class Extra{
        public int total(int[] arr) {
                int total = 0;
                for(int i = 0; i < arr.length; i++) {
                        total += arr[i];
                }
                return total;
                
        }
}

