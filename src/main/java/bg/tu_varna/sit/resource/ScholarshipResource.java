package bg.tu_varna.sit.resource;

import bg.tu_varna.sit.api.inputoutput.scholarship.achievement.apply.AchievementScholarshipApplyOperation;
import bg.tu_varna.sit.api.inputoutput.scholarship.achievement.apply.AchievementScholarshipApplyRequest;
import bg.tu_varna.sit.api.inputoutput.scholarship.firstyear.FirstYearScholarshipApplyRequest;
import bg.tu_varna.sit.api.inputoutput.scholarship.firstyear.FirstYearScholarshipApplyOperation;
import bg.tu_varna.sit.api.inputoutput.scholarship.foreign.ForeignScholarshipApplyRequest;
import bg.tu_varna.sit.api.inputoutput.scholarship.foreign.ForeignScholarshipOperation;
import bg.tu_varna.sit.api.inputoutput.scholarship.merit.MeritScholarshipApplyOperation;
import bg.tu_varna.sit.api.inputoutput.scholarship.merit.MeritScholarshipApplyRequest;
import bg.tu_varna.sit.api.inputoutput.scholarship.meritincome.MeritIncomeScholarshipApplyOperation;
import bg.tu_varna.sit.api.inputoutput.scholarship.meritincome.MeritIncomeScholarshipApplyRequest;
import bg.tu_varna.sit.api.inputoutput.scholarship.social.SocialScholarshipApplyOperation;
import bg.tu_varna.sit.api.inputoutput.scholarship.social.SocialScholarshipApplyRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/form/scholarship")
//@Authenticated
public class ScholarshipResource {

    @Inject
    AchievementScholarshipApplyOperation achievementScholarshipApplyOperation;
    @Inject
    FirstYearScholarshipApplyOperation firstYearScholarshipApplyOperation;
    @Inject
    ForeignScholarshipOperation foreignScholarshipOperation;
    @Inject
    MeritIncomeScholarshipApplyOperation meritIncomeScholarshipApplyOperation;
    @Inject
    MeritScholarshipApplyOperation meritScholarshipApplyOperation;
    @Inject
    SocialScholarshipApplyOperation socialScholarshipApplyOperation;

    @POST
    @Path("/achievement")
    public Response applyForSpecialAchievement(AchievementScholarshipApplyRequest input) {
        return Response.status(201)
                .entity(achievementScholarshipApplyOperation.process(input))
                .build();
    }

    @POST
    @Path("/firstyear")
    public Response applyForFirstYear(FirstYearScholarshipApplyRequest input) {
        return Response.status(201)
                .entity(firstYearScholarshipApplyOperation.process(input))
                .build();
    }

    @POST
    @Path("/foreign")
    public Response applyForForeign(ForeignScholarshipApplyRequest input) {
        return Response.status(201)
                .entity(foreignScholarshipOperation.process(input))
                .build();
    }

    @POST
    @Path("/merit")
    public Response applyForMerit(MeritScholarshipApplyRequest input) {
        return Response.status(201)
                .entity(meritScholarshipApplyOperation.process(input))
                .build();
    }

    @POST
    @Path("/meritincome")
    public Response applyForMeritWithIncome(MeritIncomeScholarshipApplyRequest input) {
        return Response.status(201)
                .entity(meritIncomeScholarshipApplyOperation.process(input))
                .build();
    }

    @POST
    @Path("/social")
    public Response applyForSocial(SocialScholarshipApplyRequest input) {
        return Response.status(201)
                .entity(socialScholarshipApplyOperation.process(input))
                .build();
    }

}
