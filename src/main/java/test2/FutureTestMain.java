package test2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.devjones.obpc.domain.Product;

public class FutureTestMain {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        System.out.println("[작업 처리 요청");
        class Task implements Runnable {
            Product result;
            public Task(Product result) {
                this.result = result;
            }

            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i < 10; i++) {
                    sum += i;
                }
//                result.addValue(sum);
                result.setTotal(sum);
            }
        }
        
        // 공유객체(외부객체)
        Product result = new Product();
        // 두개의 작업을 정의
        Runnable task1 = new Task(result);
        Runnable task2 = new Task(result);

        Future<Product> future1 = executorService.submit(task1, result);
        Future<Product> future2 = executorService.submit(task2, result);

        try {
            result = future1.get();
            result = future2.get();
            System.out.println("[처리 결과]" + result.getTotal());
            System.out.println("[작업 처리 완료");
        }
        catch (InterruptedException e) {}
        catch (ExecutionException e) {}
    }
}
