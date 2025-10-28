package O2_tests;

import O1_base.BaseTest_Allure;
import O1_base.BaseTest_Extended;
import O3_utils.PropertyReader;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import O3_utils.JsonReader;

import java.io.IOException;
import java.sql.SQLException;


//@Listeners(AllureListener.class)
@Epic("Web Application Testing")
@Feature("Text Box Feature")

//@Listeners(O2_tests.TestListener.class)
public class dataDriven extends BaseTest_Allure {

    @Test(priority = 1, groups = {"regression"})
//    @Description("Verify jsonTest")
//    @Severity(SeverityLevel.CRITICAL)
//    @Story("Login functionality")
    public void jsonTest() throws IOException {
        JsonReader js = new JsonReader();
        String name = js.getData(1);
        System.out.println(name);
        //Master Branch V1
        // Remote Main Branch

    }

    @Test(priority = 2)
    @Description("Verify propertyReader")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login functionality")
    public void propertyReader() throws IOException {
        PropertyReader pp = new PropertyReader();
        String name = pp.getKey("username");
        System.out.println(name);

    }

    @Test(priority = 3)
    @Description("Verify ExcelReader")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login functionality")
    public void ExcelReader() throws IOException {

    }

    @Test(priority = 4, groups = {"regression"})
    @Description("Verify DbReader")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login functionality")
    public void DbReader() throws IOException, SQLException {
//        DBUtils db = new DBUtils();
//        db.connect();
//
//        String query = "select NameEn, SupplierId from products  where NameEn like 'De%'";
//        List<Map<String, Object>> results = db.executeQuery(query);
//
//        for (Map<String, Object> row : results) {
//            System.out.println(row);
//        }
//
//        db.disconnect();
    }


}
