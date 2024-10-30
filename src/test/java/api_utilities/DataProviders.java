package api_utilities;

import api_utilities.XLUtilities;
import lombok.Data;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "Data")
    public static String [][] getAlldata() throws IOException {
        String path = System.getProperty("user.dir")+"//testData//UsersData.xlsx";
        XLUtilities xl = new XLUtilities(path);

        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1",1);

        String apidata [][] = new String [rownum][colcount];

        for (int i =1; i<= rownum; i++){
            for (int j=0; j< colcount; j++){
                apidata[i-1][j] = xl.getCellData("Sheet1",i,j);
            }
        }
        return apidata;
    }
    @DataProvider(name = "UserNames")
    public static Object[][] getUserNames() throws IOException {
        String path = System.getProperty("user.dir")+"//testData//UsersData.xlsx";
            XLUtilities xl=new XLUtilities(path);

            int rownum=xl.getRowCount("Sheet1");

        Object[][] apidata = new String[rownum][1];
        for (int i = 1; i <= rownum; i++) {
            try {
                apidata[i - 1][0] = xl.getCellData("Sheet1", i, 1);
            } catch (Exception e) {
                e.printStackTrace(); // In ra lỗi để phát hiện vấn đề
                apidata[i - 1][0] = ""; // Đặt giá trị mặc định nếu có lỗi
            }
        }

            return apidata;

}
}
