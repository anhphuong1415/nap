Blockly.defineBlocksWithJsonArray([{
    "type": "wait_seconds",
    "message0": "Đợi %1 giây",
    "args0": [{
        "type": "field_number",
        "name": "SECONDS",
        "min": 0,
        "max": 600,
        "value": 1
    }],
    "previousStatement": null,
    "nextStatement": null,
    "colour": "%{BKY_LOOPS_HUE}"
}]);

/**
 * Generator for wait block creates call to new method
 * <code>waitForSeconds()</code>.
 */
Blockly.JavaScript['wait_seconds'] = function(block) {
    var seconds = Number(block.getFieldValue('SECONDS'));
    var code = 'waitForSeconds(' + seconds + ');\n';
    return code;
};

/**
 * Register the interpreter asynchronous function
 * <code>waitForSeconds()</code>.
 */
function initInterpreterWaitForSeconds(interpreter, globalObject) {
    // Ensure function name does not conflict with variable names.
    Blockly.JavaScript.addReservedWords('waitForSeconds');

    var wrapper = interpreter.createAsyncFunction(
        function(timeInSeconds, callback) {
            // Delay the call to the callback.
            setTimeout(callback, timeInSeconds * 1000);
        });
    interpreter.setProperty(globalObject, 'waitForSeconds', wrapper);
}