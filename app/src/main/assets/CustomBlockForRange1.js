Blockly.Blocks['playmusicnote'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/music_player.png", 30, 30, { alt: "*", flipRtl: "FALSE" }))
            .appendField("Chơi nốt nhạc")
            .appendField(new Blockly.FieldDropdown([
                ["C", "C"],
                ["C/D", "C/D"],
                ["D", "D"],
                ["D/E", "D/E"],
                ["E", "E"],
                ["F", "F"],
                ["F/G", "F/G"],
                ["G", "G"],
                ["G/A", "G/A"],
                ["A", "A"],
                ["A/B", "A/B"],
                ["B", "B"]
            ]), "Note")
            .appendField("trong ")
            .appendField(new CustomFields.FieldCalculate(0, 0, 255, 1), "Duration")
            .appendField("giây");
        this.setInputsInline(true);
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(180);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['rgb_led'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/rgb_led.png", 30, 30, { alt: "*", flipRtl: "FALSE" }))
            .setAlign(Blockly.ALIGN_CENTRE)
            .appendField("Led RGB sáng màu")
            .appendField(new Blockly.FieldColour("#00cccc"), "color_left")
            .appendField(new Blockly.FieldColour("#00cccc"), "color_right")
            .appendField("trong ")
            .appendField(new CustomFields.FieldCalculate(0, 0, 255, 1), "duration")
            .appendField("giây");
        this.setInputsInline(true);
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(180);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['robot_move'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new Blockly.FieldDropdown([
                ["Tiến", "Tiến"],
                ["Lùi", "Lùi"],
                ["Rẽ trái", "Rẽ trái"],
                ["Rẽ phải", "Rẽ phải"]
            ]), "Direction")
            .appendField("với vận tốc")
            .appendField(new CustomFields.FieldVelocity(0, 0, 255, 1), "Velocity")
            .appendField("trong")
            .appendField(new CustomFields.FieldCalculate(0, 0, 255, 1), "Duration")
            .appendField("giây");
        this.setInputsInline(true);
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(195);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['playwithmatrix'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/dot_matrix.png", 30, 30, { alt: "*", flipRtl: "FALSE" }))
            .appendField("Ma trận Led ở")
            .appendField(new Blockly.FieldDropdown([
                ["Port 1", "Port 1"],
                ["Port 2", "Port 2"],
                ["Port 3", "Port 3"],
                ["Port 4", "Port 4"]
            ]), "Port")
            .appendField(" hiển thị ")
            .appendField(new CustomFields.FieldMatrix(), "Map")
            .appendField("trong")
            .appendField(new CustomFields.FieldCalculate(0, 0, 255, 1), "Duration")
            .appendField("giây");
        this.setInputsInline(false);
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(180);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['playwithmatrixledchar'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/dot_matrix.png", 30, 30, { alt: "*", flipRtl: "FALSE" }))
            .appendField("Ma trận Led ở")
            .appendField(new Blockly.FieldDropdown([
                ["Port 1", "Port 1"],
                ["Port 2", "Port 2"],
                ["Port 3", "Port 3"],
                ["Port 4", "Port 4"]
            ]), "Port")
            .appendField(" hiển thị chữ")
            .appendField(new Blockly.FieldTextInput("A"), "char")
            .appendField("trong")
            .appendField(new CustomFields.FieldCalculate(0, 0, 255, 1), "Duration")
            .appendField("giây");
        this.setInputsInline(false);
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(180);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['matrixlebspecialchar'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/dot_matrix.png", 30, 30, { alt: "*", flipRtl: "FALSE" }))
            .appendField("Ma trận Led ở")
            .appendField(new Blockly.FieldDropdown([
                ["Port 1", "Port 1"],
                ["Port 2", "Port 2"],
                ["Port 3", "Port 3"],
                ["Port 4", "Port 4"]
            ]), "Port")
            .appendField(" hiển thị ")
            .appendField(new Blockly.FieldDropdown([
                [{ "src": "Utils/BlockIcon/arrow_l2r.png", "width": 15, "height": 15, "alt": "*" }, "arrowl2r"],
                [{ "src": "Utils/BlockIcon/arrow_r2l.png", "width": 15, "height": 15, "alt": "*" }, "arrowr2l"],
                [{ "src": "Utils/BlockIcon/arrow_up.png", "width": 15, "height": 15, "alt": "*" }, "arrow_up"],
                [{ "src": "Utils/BlockIcon/arrow_down.png", "width": 15, "height": 15, "alt": "*" }, "arrow_down"],
                [{ "src": "Utils/BlockIcon/heart.png", "width": 15, "height": 15, "alt": "*" }, "heart"],
                [{ "src": "Utils/BlockIcon/smile.png", "width": 15, "height": 15, "alt": "*" }, "smile"],
                [{ "src": "Utils/BlockIcon/cute_star.png", "width": 15, "height": 15, "alt": "*" }, "star"],
                [{ "src": "Utils/BlockIcon/effect_1.gif", "width": 15, "height": 15, "alt": "*" }, "effect_1"],
                [{ "src": "Utils/BlockIcon/effect_2.gif", "width": 15, "height": 15, "alt": "*" }, "effect_2"],
                [{ "src": "Utils/BlockIcon/effect_3.gif", "width": 15, "height": 15, "alt": "*" }, "effect_3"]
            ]), "NAME")
            .appendField("trong")
            .appendField(new CustomFields.FieldCalculate(0, 0, 255, 1), "Duration")
            .appendField("giây");
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(230);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['turnoffledrbg'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/rgb_led.png", 30, 30, { alt: "*", flipRtl: "FALSE" }))
            .setAlign(Blockly.ALIGN_CENTRE)
            .appendField("Leb RBG")
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/light_on.png", 30, 30, { alt: "*", flipRtl: "FALSE" }))
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/arrow_l2r.png", 30, 30, { alt: "*", flipRtl: "FALSE" }))
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/light_off.png", 30, 30, { alt: "*", flipRtl: "FALSE" }));
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(230);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['ringled'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/ring_led.png", 30, 30, { alt: "*", flipRtl: "FALSE" }))
            .appendField("Ring Led ở")
            .appendField(new Blockly.FieldDropdown([
                ["Port 1", "Port 1"],
                ["Port 2", "Port 2"],
                ["Port 3", "Port 3"],
                ["Port 5", "Port 5"]
            ]), "ringLedModule")
            .appendField("phát sáng như");
        this.appendDummyInput()
            .appendField("Led 1: ")
            .appendField(new Blockly.FieldColour("#ff6666"), "Led_1")
            .appendField("Led 2: ")
            .appendField(new Blockly.FieldColour("#990000"), "Led_2")
            .appendField("Led 3: ")
            .appendField(new Blockly.FieldColour("#009900"), "Led_3")
            .appendField("Led 4: ")
            .appendField(new Blockly.FieldColour("#009900"), "Led_4");
        this.appendDummyInput()
            .appendField("Led 5: ")
            .appendField(new Blockly.FieldColour("#3366ff"), "Led_5")
            .appendField("Led 6: ")
            .appendField(new Blockly.FieldColour("#000099"), "Led_6")
            .appendField("Led 7: ")
            .appendField(new Blockly.FieldColour("#cc33cc"), "Led_7")
            .appendField("Led 8: ")
            .appendField(new Blockly.FieldColour("#66ffff"), "Led_8");
        this.appendDummyInput()
            .appendField("Led 9: ")
            .appendField(new Blockly.FieldColour("#ff99ff"), "Led_9")
            .appendField("Led 10: ")
            .appendField(new Blockly.FieldColour("#33cc00"), "Led_10")
            .appendField("Led 11: ")
            .appendField(new Blockly.FieldColour("#33ffff"), "Led_11")
            .appendField("Led 12: ")
            .appendField(new Blockly.FieldColour("#ffff66"), "Led_12");
        this.setInputsInline(false);
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(230);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['servo'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Servo")
            .appendField(new Blockly.FieldDropdown([
                [{ "src": "Utils/BlockIcon/left_servo.png", "width": 15, "height": 15, "alt": "*" }, "Servo_1"],
                [{ "src": "Utils/BlockIcon/right_servo.png", "width": 15, "height": 15, "alt": "*" }, "Servo_2"],
                [{ "src": "Utils/BlockIcon/both_servo.png", "width": 15, "height": 15, "alt": "*" }, "Both"]
            ]), "Servo_Select")
            .appendField("quay tới góc")
            .appendField(new Blockly.FieldAngle(90), "Angle");
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(165);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['motorselect'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Motor")
            .appendField(new Blockly.FieldDropdown([
                [{ "src": "Utils/BlockIcon/left_motor.png", "width": 15, "height": 15, "alt": "*" }, "Left"],
                [{ "src": "Utils/BlockIcon/right_motor.png", "width": 15, "height": 15, "alt": "*" }, "Right"],
                [{ "src": "Utils/BlockIcon/both_motor.png", "width": 15, "height": 15, "alt": "*" }, "Both"]
            ]), "MotorSelect")
            .appendField("quay với vận tốc")
            .appendField(new CustomFields.FieldVelocity(1, 1, 255, 1), "velocity")
            .appendField("trong")
            .appendField(new CustomFields.FieldCalculate(1, 0, 255, 1), "duration")
            .appendField("giây");
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(90);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['srf05'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/object_sensor.png", 30, 30, { alt: "*", flipRtl: "FALSE" }))
            .appendField("Khoảng cách tới vật cản đo được sử dụng cảm biến ở")
            .appendField(new Blockly.FieldDropdown([
                ["Port 1", "Port 1"],
                ["Port 2", "Port 2"],
                ["Port 3", "Port 3"],
                ["Port 4", "Port 4"]
            ]), "port");
        this.setOutput(true, null);
        this.setColour(180);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['light_sensor'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/light_sensor.png", 30, 30, { alt: "*", flipRtl: "FALSE" }))
            .appendField("Cường độ ánh sáng đo được sử dụng cảm biến ở")
            .appendField(new Blockly.FieldDropdown([
                ["Port 1", "Port 1"],
                ["Port 2", "Port 2"],
                ["Port 3", "Port 3"],
                ["Port 5", "Port 5"]
            ]), "Port");
        this.setOutput(true, null);
        this.setColour(230);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['colorsensor'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/color_sensor.png", 30, 30, { alt: "*", flipRtl: "FALSE" }))
            .appendField("Màu sắc được xác định bằng cảm biến ở")
            .appendField(new Blockly.FieldDropdown([
                ["Port 1", "Port 1"],
                ["Port 2", "Port 2"],
                ["Port 3", "Port 3"],
                ["Port 6", "Port 6"]
            ]), "Port");
        this.setOutput(true, null);
        this.setColour(75);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['sound_sensor'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/sound_sensor.png", 30, 30, { alt: "*", flipRtl: "FALSE" }))
            .appendField("Âm thanh được cảm nhận bằng cảm biến ở")
            .appendField(new Blockly.FieldDropdown([
                ["Port 1", "Port 1"],
                ["Port 2", "Port 2"],
                ["Port 3", "Port 3"],
                ["Port 7", "Port 7"]
            ]), "Port")
            .appendField("để phát hiện âm thanh");
        this.setOutput(true, null);
        this.setColour(230);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['path_detecter'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new Blockly.FieldImage("Utils/BlockIcon/path_sensor.png", 30, 30, { alt: "*", flipRtl: "FALSE" }))
            .appendField("Hành trình đi được đo bằng cảm biến ở")
            .appendField(new Blockly.FieldDropdown([
                ["Port 1", "Port 1"],
                ["Port 2", "Port 2"],
                ["Port 3", "Port 3"],
                ["Port 8", "Port 8"]
            ]), "Port");
        this.appendDummyInput()
            .appendField("theo chiều")
            .appendField(new Blockly.FieldDropdown([
                ["bên trái", "bên trái"],
                ["bên phải", "bên phải"]
            ]), "side")
            .appendField("với màu sắc")
            .appendField(new Blockly.FieldDropdown([
                ["màu trắng", "màu trắng"],
                ["màu đen", "màu đen"]
            ]), "color");
        this.setOutput(true, null);
        this.setColour(60);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['dummy_play_block'] = {
    init: function() {
        this.appendDummyInput()
            .setAlign(Blockly.ALIGN_CENTRE)
            .appendField("Nhấn nút")
            .appendField(new Blockly.FieldImage("Utils/Icon/png/Start.png", 50, 50, { alt: "*", flipRtl: "FALSE" }))
            .appendField("để bắt đầu chương trình");
        this.setInputsInline(true);
        this.setNextStatement(true, null);
        this.setColour(230);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['if_block'] = {
    init: function() {
        this.appendValueInput("condition")
            .setCheck("Boolean")
            .appendField("Nếu");
        this.appendStatementInput("if block")
            .setCheck(null)
            .setAlign(Blockly.ALIGN_CENTRE)
            .appendField("Đúng thì");
        this.appendStatementInput("else block")
            .setCheck(null)
            .appendField("Sai thì");
        this.setInputsInline(false);
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(75);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['if1'] = {
    init: function() {
        this.appendValueInput("condition")
            .setCheck("Boolean")
            .appendField("Nếu");
        this.appendStatementInput("command")
            .setCheck(null)
            .appendField("Đúng thì");
        this.setInputsInline(false);
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(315);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['break_continue_loop'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new Blockly.FieldDropdown([
                ["nhảy vòng lặp", "nhảy vòng lặp"],
                ["thoát vòng lặp", "thoát vòng lặp"]
            ]), "action");
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(330);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['loop'] = {
    init: function() {
        this.appendStatementInput("command")
            .setCheck(null)
            .appendField("Thực hiện");
        this.appendDummyInput()
            .appendField("lặp lại")
            .appendField(new CustomFields.FieldCalculate(0, 0, 100, 1), "NAME")
            .appendField("lần");
        this.setInputsInline(false);
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(285);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['stop_move'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Dừng động cơ");
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setColour(0);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['create_function'] = {
    init: function() {
        this.appendDummyInput()
            .appendField("Để")
            .appendField(new Blockly.FieldTextInput("di chuyển"), "Function_name");
        this.appendStatementInput("NAME")
            .setCheck(null);
        this.setColour(230);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['test_pitch_field'] = {
    init: function() {
        this.appendDummyInput()
            .appendField('pitch')
            .appendField(new CustomFields.FieldPitch('7'), 'PITCH');
        this.setStyle('loop_blocks');
    }
};

Blockly.Blocks['while'] = {
    init: function() {
        // this.appendValueInput("condition")
        //     .setCheck("Boolean")
        //     .appendField("Khi mà");
        this.appendStatementInput("actions")
            .setCheck(null)
            .appendField("Thực hiện liên tục");
        this.setColour(285);
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
        this.setTooltip("");
        this.setHelpUrl("");
    }
};

Blockly.Blocks['test_cal_field'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new CustomFields.FieldCalculate(0, 0, 1000, 1), 'NAME');
        this.setColour(285);
    }
};

Blockly.Blocks['test_vel_field'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new CustomFields.FieldVelocity(0, 0, 1000, 1), 'NAME');
        this.setColour(285);
    }
};

Blockly.Blocks['field_matrix'] = {
    init: function() {
        this.appendDummyInput()
            .appendField(new CustomFields.FieldMatrix(1, 16), 'NAME');
        this.setColour(285);
    }
};