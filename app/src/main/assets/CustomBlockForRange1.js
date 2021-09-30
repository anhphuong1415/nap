Blockly.Blocks['move'] = {
    init: function() {
        this.appendDummyInput()
            .setAlign(Blockly.ALIGN_CENTRE)
            .appendField("Di chuyển")
            .appendField(new Blockly.FieldDropdown([
                ["khoảng cách(m)", "distance"],
                ["thời gian(s) ", "duration"],
            ]), "Mode");
        this.appendValueInput("moveValue")
            .setCheck("Number")
            .appendField("moveValue");
        this.appendValueInput("speed")
            .setCheck("Number")
            .appendField("Tốc độ");
        this.setInputsInline(false);
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(315);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['playmusicnote'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Chơi nốt nhạc")
            .appendField("Nốt nhạc: ")
            .appendField(new Blockly.FieldDropdown([
                ["Đô", "Đô"],
                ["Rê", "Rê"],
                ["Mi", "Mi"],
                ["Pa", "Pa"],
                ["Son", "Son"],
                ["La", "La"],
                ["Si", "Si"]
            ]), "Note");
        this.setInputsInline(false);
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(230);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['playwithled'] = {
    init: function() {
        this.appendValueInput("NAME")
            .setCheck("String")
            .setAlign(Blockly.ALIGN_CENTRE)
            .appendField("Hiển thị:");
        this.appendDummyInput()
            .appendField("Kiểu đổi chữ")
            .appendField(new Blockly.FieldDropdown([
                ["Left", "LeftMode"],
                ["Up", "UpMode"],
                ["Disappear", "DisappearMode"]
            ]), "TranferMode");
        this.setInputsInline(false);
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(180);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['dir'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Quay")
            .appendField(new Blockly.FieldDropdown([
                ["Left", "LEFT"],
                ["Right", "RIGHT"],
                ["Back", "BACK"]
            ]), "Dir");
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(230);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['rgb_led'] = {
    init: function() {
        this.appendDummyInput()
            .setAlign(Blockly.ALIGN_CENTRE)
            .appendField("Led RGB");
        this.appendValueInput("Led Index")
            .setCheck("Number")
            .setAlign(Blockly.ALIGN_CENTRE)
            .appendField("Index");
        this.appendValueInput("Red")
            .setCheck("Number")
            .setAlign(Blockly.ALIGN_CENTRE)
            .appendField("Red");
        this.appendValueInput("Green")
            .setCheck("Number")
            .setAlign(Blockly.ALIGN_CENTRE)
            .appendField("Green");
        this.appendValueInput("Blue")
            .setCheck("Number")
            .setAlign(Blockly.ALIGN_CENTRE)
            .appendField("Blue");
        this.setInputsInline(true);
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(300);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};