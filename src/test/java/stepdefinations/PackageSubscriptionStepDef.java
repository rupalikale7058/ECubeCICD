package stepdefinations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import pages.PackageSubscriptionPage;
import util.ReportLogger;

import java.util.List;
import java.util.Map;

public class PackageSubscriptionStepDef {
    ReportLogger reportLogger = ReportLogger.getInstance();

    @And("User select package type")
    public void user_Select_Package_Type() {
        new PackageSubscriptionPage().clickOnViewAllLink();
    }

    @And("User click on add option")
    public void user_Click_On_Add_Option() {
        new PackageSubscriptionPage().clickOnPlusIcon();
    }

    @And("User enter the Member Name")
    public void user_Enter_The_MemberName(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> row = data.getFirst();
        String memberName = row.get("MemberName");
        new PackageSubscriptionPage().enterMemberName(memberName);
    }

    @And("User select MemberName checkBox")
    public void user_Select_MemberName_CheckBox() throws InterruptedException {
        new PackageSubscriptionPage().checkCheckBoxToSelectMemberName();
    }

    @And("User click on save button")
    public void user_Click_On_Save_Button() throws InterruptedException {
        new PackageSubscriptionPage().clickOnSaveButton();
    }


}
