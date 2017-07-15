package models.builders;

import models.viewmodels.ReportHelperViewModel;

/**
 * Created by boyanbonev on 10/01/2017.
 */
public interface IReportHelperBuilder {
    ReportHelperViewModel Build(int id);
}
