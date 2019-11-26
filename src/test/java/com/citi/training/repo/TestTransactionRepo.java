package com.citi.training.repo;

import com.citi.training.entities.Transaction;
import com.citi.training.repositories.TransactionRepo;
import com.citi.training.services.TransactionService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest // use an in memory database
@SpringBootTest(classes={com.citi.training.Application.class})
public class TestTransactionRepo {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TestEntityManager manager;

//    @Test
//    public void transactionServiceCanFindByTransactionBetween() {
//        Transaction trans = new Transaction(1, "C", "BUY", 40.0, -40.0, "n", "20:30:10");
//        manager.persist(trans);
//        Iterable<Transaction> transactions = transactionService.("20:00:00", "20:45:09");
//        boolean result = false;
//        for (Transaction t : transactions) {
//            if (t.getTransactionTime().equals("20:30:10")) {
//                result = true;
//                break;
//            }
//        }
//        assertTrue(result);
//    }

    public void transactionServiceCanFindLatestTransactionById(){
        Transaction trans = new Transaction(1, "C", "BUY", 40.0, -40.0, "n", "20:30:10");
        manager.persist(trans);
        boolean result = false;

        if(transactionService.getLatestTransactionById(1).getTransactionTime().equals("20:30:10")){
            result = true;
        }
        assertTrue(result);
    }



}
