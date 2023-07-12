package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistory {
    public String transactionType;
    public String transactionDate;
    public String destination;
    public String origin;
    public String amount;
}
