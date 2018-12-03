package org.intelliflow.csi.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlocklyController {
	@RequestMapping("/blockly")
    public String blockly(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "blockly";
    }
	
	@RequestMapping("/toolboxcontent")
    public String toolboxcontent(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "toolboxcontent";
    }
	
	@RequestMapping("/javascript")
    public String blockly(Model model) {
        //model.addAttribute("name", name);
        return "javascript";
    }
}
