import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) //Mocking을 할 수 있는 환경 //Mock: 가짜(모조품) 객체
public class DollarCalculatorTest {

    @Mock
    public MarketApi marketApi;

    @BeforeEach //test실행 이전
    public void init(){
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
        //응답을 예상(기대)하고 리턴값을 적음
        //marketApi.connect()가 일어날 때 return 1100;이 아니라 return 3000이 됨
    }

    @Test
    public void testHello(){
        System.out.println("hello");
    }

    @Test
    public void dollarTest(){
        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        Assertions.assertEquals(22000, calculator.sum(10,10)); //기댓값, 들어갈 값
        Assertions.assertEquals(0, calculator.minus(10,10));
    }

    @Test
    public void MockTest(){
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);
        System.out.println(calculator.sum(10,10));
        Assertions.assertEquals(60000, calculator.sum(10,10)); //기댓값, 들어갈 값
        Assertions.assertEquals(0, calculator.minus(10,10));
    }

}
