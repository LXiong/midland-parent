package com.midland.web.controller;

import com.midland.web.commons.FastJsonUtils;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.Appointment;
import com.midland.web.service.AppointmentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/appoint")
public class WebAppointController {
    @Resource
    private AppointmentService appointmentServiceImpl;
    @RequestMapping(value="rest", produces="text/html;charset=UTF-8")
    public Object addAppoint(){
        Appointment obj = new Appointment();
        Result result = new Result();
        try {
            appointmentServiceImpl.insertAppointment(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        }
        return  FastJsonUtils.toJSONStr(result);
    }
}
