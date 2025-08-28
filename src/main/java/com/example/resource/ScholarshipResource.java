package com.example.resource;

import com.example.api.inputoutput.scholarship.achievement.apply.AchievementScholarshipApplyOperation;
import com.example.api.inputoutput.scholarship.achievement.apply.AchievementScholarshipApplyRequest;
import com.example.api.inputoutput.scholarship.firstyear.FirstYearScholarshipApplyOperation;
import com.example.api.inputoutput.scholarship.firstyear.FirstYearScholarshipApplyRequest;
import com.example.api.inputoutput.scholarship.foreign.ForeignScholarshipApplyRequest;
import com.example.api.inputoutput.scholarship.foreign.ForeignScholarshipOperation;
import com.example.api.inputoutput.scholarship.merit.MeritScholarshipApplyOperation;
import com.example.api.inputoutput.scholarship.merit.MeritScholarshipApplyRequest;
import com.example.api.inputoutput.scholarship.meritincome.MeritIncomeScholarshipApplyOperation;
import com.example.api.inputoutput.scholarship.meritincome.MeritIncomeScholarshipApplyRequest;
import com.example.api.inputoutput.scholarship.social.SocialScholarshipApplyOperation;
import com.example.api.inputoutput.scholarship.social.SocialScholarshipApplyRequest;
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
