Blockly.JavaScript['playmusicnote'] = function(block) {
    var dropdown_note = block.getFieldValue('Note');
    var duration = block.getFieldValue('Duration');
    // TODO: Assemble JavaScript into code variable.
    if (dropdown_note == "C") var c = 1;
    else if (dropdown_note == "C/D") var c = 2;
    else if (dropdown_note == "D") var c = 3;
    else if (dropdown_note == "D/E") var c = 4;
    else if (dropdown_note == "E") var c = 5;
    else if (dropdown_note == "F") var c = 6;
    else if (dropdown_note == "F/G") var c = 7;
    else if (dropdown_note == "G") var c = 8;
    else if (dropdown_note == "G/A") var c = 9;
    else if (dropdown_note == "A") var c = 10;
    else if (dropdown_note == "A/B") var c = 11;
    else if (dropdown_note == "B") var c = 12;
    var code = 'Android.sendCmd(' + '2,0,9,' + duration + ',' + c + ',0,0,[]);\n'
    return code;
};

Blockly.JavaScript['rgb_led'] = function(block) {
    var colour_color = block.getFieldValue('color');
    var number_duration = block.getFieldValue('duration');
    var code = 'Android.sendCmd(' + '2,0,8,' + number_duration + ',' + colour_color + ',0,0,[]);\n';
    return code;
};

Blockly.JavaScript['robot_move'] = function(block) {
    var dropdown_direction = block.getFieldValue('Direction');
    var number_velocity = block.getFieldValue('Velocity');
    var number_duration = block.getFieldValue('Duration');
    if (dropdown_direction == "Tiến") var dir = 1;
    else if (dropdown_direction == "Lùi") var dir = 2;
    else if (dropdown_direction == "Rẽ trái") var dir = 3;
    else if (dropdown_direction == "Rẽ phải") var dir = 4;
    else dir = 0;
    var code = 'Android.sendCmd(' + '2,0,5,' + number_duration + ',' + number_velocity + ',' + dir + ',0,[]);\n';
    return code;
};

Blockly.JavaScript['playwithmatrixledchar'] = function(block) {
    var dropdown_port = block.getFieldValue('Port');
    var text_char = block.getFieldValue('char');
    if (dropdown_port == "Port 1") var port = 1;
    else if (dropdown_port == "Port 2") var port = 2;
    else if (dropdown_port == "Port 3") var port = 3;
    else if (dropdown_port == "Port 4") var port = 4;
    else if (dropdown_port == "Port 5") var port = 5;
    else if (dropdown_port == "Port 6") var port = 6;
    else if (dropdown_port == "Port 7") var port = 7;
    else if (dropdown_port == "Port 8") var port = 8;
    var code = 'Android.sendCmd(' + '2,' + port + ',7,0,' + '(int)' + "'" + text_char + "'" + ',0,0,[]);\n';
    return code;
};

Blockly.JavaScript['matrixlebspecialchar'] = function(block) {
    var dropdown_port = block.getFieldValue('module');
    var dropdown_name = block.getFieldValue('NAME');
    // TODO: Assemble JavaScript into code variable.
    if (dropdown_port == "Port 1") var port = 1;
    else if (dropdown_port == "Port 2") var port = 2;
    else if (dropdown_port == "Port 3") var port = 3;
    else if (dropdown_port == "Port 4") var port = 4;
    else if (dropdown_port == "Port 5") var port = 5;
    else if (dropdown_port == "Port 6") var port = 6;
    else if (dropdown_port == "Port 7") var port = 7;
    else if (dropdown_port == "Port 8") var port = 8;

    if (dropdown_name == "arrowl2r") var vl = 300;
    else if (dropdown_name == "arrowr2l") var vl = 301;
    else if (dropdown_name == "arrow_up") var vl = 302;
    else if (dropdown_name == "arrow_down") var vl = 303;
    else if (dropdown_name == "heart") var vl = 304;
    else if (dropdown_name == "smile") var vl = 305;
    else if (dropdown_name == "star") var vl = 306;
    else if (dropdown_name == "effect_1") var vl = 307;
    else if (dropdown_name == "effect_2") var vl = 308;
    else if (dropdown_name == "effect_3") var vl = 309;

    var code = 'Android.sendCmd(' + '2,' + port + ',7,0,' + vl + ',0,0,[]);\n';
    return code;
};

Blockly.JavaScript['turnoffledrbg'] = function(block) {
    // TODO: Assemble JavaScript into code variable.
    var code = 'Android.sendCmd(' + '0,0,8,0,0,0,0,[]);\n';
    return code;
};

Blockly.JavaScript['ringled'] = function(block) {
    var dropdown_port = block.getFieldValue('ringLedModule');
    var colour_led_1 = block.getFieldValue('Led_1');
    var colour_led_2 = block.getFieldValue('Led_2');
    var colour_led_3 = block.getFieldValue('Led_3');
    var colour_led_4 = block.getFieldValue('Led_4');
    var colour_led_5 = block.getFieldValue('Led_5');
    var colour_led_6 = block.getFieldValue('Led_6');
    var colour_led_7 = block.getFieldValue('Led_7');
    var colour_led_8 = block.getFieldValue('Led_8');
    var colour_led_9 = block.getFieldValue('Led_9');
    var colour_led_10 = block.getFieldValue('Led_10');
    var colour_led_11 = block.getFieldValue('Led_11');
    var colour_led_12 = block.getFieldValue('Led_12');

    if (dropdown_port == "Port 1") var port = 1;
    else if (dropdown_port == "Port 2") var port = 2;
    else if (dropdown_port == "Port 3") var port = 3;
    else if (dropdown_port == "Port 4") var port = 4;
    else if (dropdown_port == "Port 5") var port = 5;
    else if (dropdown_port == "Port 6") var port = 6;
    else if (dropdown_port == "Port 7") var port = 7;
    else if (dropdown_port == "Port 8") var port = 8;

    var code = 'Android.sendCmd(' + '2,' + port + ',10,0,0,0,0,' +
        '[' + '"' + colour_led_1 + '"' + ', ' +
        '"' + colour_led_2 + '"' + ', ' +
        '"' + colour_led_3 + '"' + ', ' +
        '"' + colour_led_4 + '"' + ', ' +
        '"' + colour_led_5 + '"' + ', ' +
        '"' + colour_led_6 + '"' + ', ' +
        '"' + colour_led_7 + '"' + ', ' +
        '"' + colour_led_8 + '"' + ', ' +
        '"' + colour_led_9 + '"' + ', ' +
        '"' + colour_led_10 + '"' + ', ' +
        '"' + colour_led_11 + '"' + ', ' +
        '"' + colour_led_12 + '"' + ']);\n'
    return code;
};

Blockly.JavaScript['servo'] = function(block) {
    var dropdown_servo_select = block.getFieldValue('Servo_Select');
    var angle_angle = block.getFieldValue('Angle');
    if (dropdown_servo_select == "Servo_1") var servo = 1;
    else if (dropdown_servo_select == "Servo_2") var servo = 2;
    else if (dropdown_servo_select == "Both") var servo = 3;
    var code = 'Android.sendCmd(' + '2,0,11,0,' + angle_angle + ',0,' + servo + ',[]);\n';
    return code;
};

Blockly.JavaScript['motorselect'] = function(block) {
    var dropdown_motorselect = block.getFieldValue('MotorSelect');
    var number_velocity = block.getFieldValue('velocity');
    var number_duration = block.getFieldValue('duration');
    if (dropdown_motorselect == "Left") var motor = 1;
    else if (dropdown_motorselect == "Right") var motor = 2;
    else if (dropdown_motorselect == "Both") var motor = 3;
    var code = 'Android.sendCmd(' + '2,0,5,' + number_duration + ',' + number_velocity + ',' + dir + ',' + motor + ',[]);\n';
    return code;
};

Blockly.JavaScript['srf05'] = function(block) {
    var dropdown_port = block.getFieldValue('port');
    if (dropdown_port == "Port 1") var port = 1;
    else if (dropdown_port == "Port 2") var port = 2;
    else if (dropdown_port == "Port 3") var port = 3;
    else if (dropdown_port == "Port 4") var port = 4;
    else if (dropdown_port == "Port 5") var port = 5;
    else if (dropdown_port == "Port 6") var port = 6;
    else if (dropdown_port == "Port 7") var port = 7;
    else if (dropdown_port == "Port 8") var port = 8;

    var code = 'Android.sendCmd(' + '1,' + port + ',1,0,0,0,0,[])';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['light_sensor'] = function(block) {
    var dropdown_port = block.getFieldValue('Port');
    if (dropdown_port == "Port 1") var port = 1;
    else if (dropdown_port == "Port 2") var port = 2;
    else if (dropdown_port == "Port 3") var port = 3;
    else if (dropdown_port == "Port 4") var port = 4;
    else if (dropdown_port == "Port 5") var port = 5;
    else if (dropdown_port == "Port 6") var port = 6;
    else if (dropdown_port == "Port 7") var port = 7;
    else if (dropdown_port == "Port 8") var port = 8;

    var code = 'Android.sendCmd(' + '1,' + port + ',3,0,0,0,0,[])';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['colorsensor'] = function(block) {
    var dropdown_port = block.getFieldValue('Port');
    if (dropdown_port == "Port 1") var port = 1;
    else if (dropdown_port == "Port 2") var port = 2;
    else if (dropdown_port == "Port 3") var port = 3;
    else if (dropdown_port == "Port 4") var port = 4;
    else if (dropdown_port == "Port 5") var port = 5;
    else if (dropdown_port == "Port 6") var port = 6;
    else if (dropdown_port == "Port 7") var port = 7;
    else if (dropdown_port == "Port 8") var port = 8;

    var code = 'Android.sendCmd(' + '1,' + port + ',4,0,0,0,0,[])';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['sound_sensor'] = function(block) {
    var dropdown_port = block.getFieldValue('Port');
    if (dropdown_port == "Port 1") var port = 1;
    else if (dropdown_port == "Port 2") var port = 2;
    else if (dropdown_port == "Port 3") var port = 3;
    else if (dropdown_port == "Port 4") var port = 4;
    else if (dropdown_port == "Port 5") var port = 5;
    else if (dropdown_port == "Port 6") var port = 6;
    else if (dropdown_port == "Port 7") var port = 7;
    else if (dropdown_port == "Port 8") var port = 8;

    var code = 'Android.sendCmd(' + '1,' + port + ',12,0,0,0,0,[])';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['path_detecter'] = function(block) {
    var dropdown_port = block.getFieldValue('Port');
    var dropdown_side = block.getFieldValue('side');
    var dropdown_color = block.getFieldValue('color');
    if (dropdown_port == "Port 1") var port = 1;
    else if (dropdown_port == "Port 2") var port = 2;
    else if (dropdown_port == "Port 3") var port = 3;
    else if (dropdown_port == "Port 4") var port = 4;
    else if (dropdown_port == "Port 5") var port = 5;
    else if (dropdown_port == "Port 6") var port = 6;
    else if (dropdown_port == "Port 7") var port = 7;
    else if (dropdown_port == "Port 8") var port = 8;

    if (dropdown_side == "left") var side = 1;
    else var side = 2;

    if (dropdown_color == "white") var colorFinding = 1;
    else var colorFinding = 2;

    var code = 'Android.sendCmd(' + '1,' + port + ',2,0,' + colorFinding + ', ' + side + ',0,[])';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['dummy_play_block'] = function(block) {
    var code = '/*___START BLOCKLY PLAYGROUND PROGRAMING__*/\n';
    return code;
};

Blockly.JavaScript['wait'] = function(block) {
    var number_time = block.getFieldValue('time');
    // TODO: Assemble JavaScript into code variable.
    var code = '...;\n';
    return code;
};

Blockly.JavaScript['if_block'] = function(block) {
    var value_condition = Blockly.JavaScript.valueToCode(block, 'condition', Blockly.JavaScript.ORDER_ATOMIC);
    var statements_if_block = Blockly.JavaScript.statementToCode(block, 'if block');
    var statements_else_block = Blockly.JavaScript.statementToCode(block, 'else block');
    var code = 'if(' + value_condition + '){\n' + statements_if_block + '\n}' +
        'else{\n' + statements_else_block + '\n}';
    return code;
};

Blockly.JavaScript['if1'] = function(block) {
    var value_condition = Blockly.JavaScript.valueToCode(block, 'condition', Blockly.JavaScript.ORDER_ATOMIC);
    var statements_command = Blockly.JavaScript.statementToCode(block, 'command');
    var code = 'if(' + value_condition + '){\n' + statements_command + '\n}';
    return code;
};

Blockly.JavaScript['break_continue_loop'] = function(block) {
    var dropdown_action = block.getFieldValue('action');
    if (dropdown_action == "nhảy vòng lặp") var st = 'continue';
    else st = 'break;'
    var code = st + ';\n';
    return code;
};

Blockly.JavaScript['loop'] = function(block) {
    var statements_command = Blockly.JavaScript.statementToCode(block, 'command');
    var number_name = block.getFieldValue('NAME');
    // TODO: Assemble JavaScript into code variable.
    var code = 'while(int i = 0; i <' + number_name + ';i++){\n' +
        statements_command + '\n}';
    return code;
};

Blockly.JavaScript['stop_move'] = function(block) {
    var code = 'Android.sendCmd(' + '0,0,5,0,0,0,0,[]);\n';
    return code;
};

Blockly.JavaScript['create_function'] = function(block) {
    var text_function_name = block.getFieldValue('Function_name');
    var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
    // TODO: Assemble JavaScript into code variable.
    var code = 'function ' + text_function_name + '(){\n' +
        statements_name + '\n}';
    return code;
};