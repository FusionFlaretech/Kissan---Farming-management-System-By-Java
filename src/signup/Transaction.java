package signup;

import java.time.LocalDateTime;

//Transaction.java
//Transaction.java
public class Transaction {
 private int transactionId;
 private int quantity;
 private int cropId;
 private String transactionType;
 private double amount;
 private LocalDateTime  transactionDate;
 private String description;

 // Constructor
 public Transaction(int transactionId,  int cropId, double amount,String transactionType,int quantity, LocalDateTime transactionDate) {
     this.transactionId = transactionId;
    
     this.cropId = cropId;
     this.transactionType = transactionType;
     
     this.transactionDate = transactionDate;
    this.quantity=quantity;
    this.amount=amount;
 }

 // Getters and Setters...

 public Transaction(int int1, int int2, String string, double double1, LocalDateTime localDateTime, String string2) {
	// TODO Auto-generated constructor stub
}

public int getTransactionId() {
     return transactionId;
 }

 public void setTransactionId(int transactionId) {
     this.transactionId = transactionId;
 }
 public int getquantity() {
     return quantity;
 }

 public void setquantity(int quantity) {
     this.quantity = quantity;
 }


 public int getCropId() {
     return cropId;
 }

 public void setCropId(int cropId) {
     this.cropId = cropId;
 }

 public String getTransactionType() {
     return transactionType;
 }

 public void setTransactionType(String transactionType) {
     this.transactionType = transactionType;
 }

 public double getAmount() {
     return amount;
 }

 public void setAmount(double amount) {
     this.amount = amount;
 }

 public LocalDateTime getTransactionDate() {
     return transactionDate;
 }

 public void setTransactionDate(LocalDateTime transactionDate) {
     this.transactionDate = transactionDate;
 }

 public String getDescription() {
     return description;
 }

 public void setDescription(String description) {
     this.description = description;
 }
}
