package com.example.transactionmanagementdemo.domain.request;

import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter

public class PurchaseRequest {
    List<String> productNames;
    List<Integer> puductQuantity;
}
