package project.customFI.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import project.customFI.member.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
}
