Blockly.JavaScript['move'] = function(block) {
    var dropdown_mode = block.getFieldValue('Mode');
    var value_movevalue = Blockly.JavaScript.valueToCode(block, 'moveValue', Blockly.JavaScript.ORDER_ATOMIC);
    var value_speed = Blockly.JavaScript.valueToCode(block, 'speed', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    if (dropdown_mode == "distance") var c = 1;
    else if (dropdown_mode == "duration") var c = 2;
    var code = 'Android.sendCmd(' + '2,5,' + value_speed + ',' + c + ',' + value_speed + ',' + value_movevalue + ',0,0,0);';
    return code;
};

Blockly.JavaScript['playmusicnote'] = function(block) {
    var dropdown_note = block.getFieldValue('Note');
    // TODO: Assemble JavaScript into code variable.
    if (dropdown_note == "Đô") var c = 1;
    else if (dropdown_note == "Rê") var c = 2;
    else if (dropdown_note == "Mi") var c = 3;
    else if (dropdown_note == "Pa") var c = 4;
    else if (dropdown_note == "Son") var c = 5;
    else if (dropdown_note == "La") var c = 6;
    else if (dropdown_note == "Si") var c = 7;
    var code = 'Android.sendCmd(' + '2,9,' + c + ',0,1,' + '0,0,0,0);';
    return code;
};

Blockly.JavaScript['playwithled'] = function(block) {
    var value_name = Blockly.JavaScript.valueToCode(block, 'NAME', Blockly.JavaScript.ORDER_ATOMIC);
    var dropdown_tranfermode = block.getFieldValue('TranferMode');
    var test = 'Led,' + dropdown_tranfermode + ',';
    // TODO: Assemble JavaScript into code variable.
    var code = 'Android.sendCmd(' + '2,7,' + '0,0,0,0,0,0,0);';
    return code;
};

Blockly.JavaScript['dir'] = function(block) {
    var dropdown_dir = block.getFieldValue('Dir');
    // TODO: Assemble JavaScript into code variable.
    if (dropdown_dir == "LEFT") var c = '10,0,0,';
    else if (dropdown_dir == "RIGHT") var c = '0,0,10,';
    else var c = '-10,0,10,';
    var code = 'Android.sendCmd(' + '2,5,' + c + '0,0,0' + ",0);";
    return code;
};

Blockly.JavaScript['rgb_led'] = function(block) {
    var Index = Blockly.JavaScript.valueToCode(block, 'Led Index', Blockly.JavaScript.ORDER_ATOMIC);
    var value_red = Blockly.JavaScript.valueToCode(block, 'Red', Blockly.JavaScript.ORDER_ATOMIC);
    var value_green = Blockly.JavaScript.valueToCode(block, 'Green', Blockly.JavaScript.ORDER_ATOMIC);
    var value_blue = Blockly.JavaScript.valueToCode(block, 'Blue', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = 'Android.sendCmd(' + '2,8,0,0' + Index + ',' + value_red + ',' + value_green + ',' + value_green + ",0);";
    return code;
};