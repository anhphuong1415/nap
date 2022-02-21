'use strict'

goog.provide('CustomFields.FieldVelocity');

goog.require('Blockly.FieldNumber');
goog.require('Blockly.Field');
goog.require('Blockly.fieldRegistry');
goog.require('Blockly.utils.math');
goog.require('Blockly.utils');
goog.require('Blockly.utils.dom');
goog.require('Blockly.utils.object');
goog.require('Blockly.utils.Size');

var CustomFields = CustomFields || {}

//contructor
CustomFields.FieldVelocity = function(opt_value, opt_min, opt_max, opt_precision) {
    this.max_ = opt_value;
    this.min_ = opt_min;
    CustomFields.FieldVelocity.superClass_.constructor.call(this, opt_value, opt_min, opt_max, opt_precision);
}
Blockly.utils.object.inherits(CustomFields.FieldVelocity, Blockly.FieldNumber);

CustomFields.FieldVelocity.prototype.initView = function() {
    CustomFields.FieldVelocity.superClass_.initView.call(this);
}

CustomFields.FieldVelocity.fromJson = function(options) {
    return new CustomFields.FieldCalculate(options['value'],
        undefined, undefined, undefined, undefined, options);
}

CustomFields.FieldVelocity.prototype.doValueUpdate_ = function(newValue) {
    CustomFields.FieldVelocity.superClass_.doValueUpdate_.call(this, newValue);
    this.displayValue_ = newValue;
    this.isValueInvalid_ = false;
}

CustomFields.FieldVelocity.prototype.showEditor_ = function() {
    CustomFields.FieldVelocity.superClass_.showEditor_.call(this);
    Blockly.WidgetDiv.show(
        this, this.sourceBlock_.RTL, this.widgetDispose_.bind(this));
    this.WidgetCreate_();
}

CustomFields.FieldVelocity.prototype.WidgetCreate_ = function() {
    var widget = this.CreateWidget_();
    Blockly.WidgetDiv.DIV.style.width = "30%";
    Blockly.WidgetDiv.DIV.style.height = "30%";
    Blockly.WidgetDiv.DIV.style.left = "30%";
    Blockly.WidgetDiv.DIV.style.top = "30%";
    Blockly.WidgetDiv.DIV.style.backgroundColor = "transparent";
    Blockly.WidgetDiv.DIV.style.display = "flex";

    var text_ = document.createElement('p');
    text_.id = 'text_vel';
    text_.textContent = 'Tốc độ: ';
    var inText = document.createElement('span');
    inText.className = 'current-value';
    inText.id = 'volumeValue';
    inText.textContent = '0';
    text_.appendChild(inText);
    Blockly.WidgetDiv.DIV.appendChild(text_);
    Blockly.WidgetDiv.DIV.appendChild(widget);
}

CustomFields.FieldVelocity.prototype.CreateWidget_ = function() {

    var knod_surround = document.createElement('div');
    knod_surround.className = 'knob_surround';

    var knod = document.createElement('div');
    knod.className = 'knob';
    knod.id = 'knob';

    var min = document.createElement('span');
    min.className = 'min';

    var max = document.createElement('span');
    max.className = 'max';

    var tickContainer = document.createElement('div');
    tickContainer.className = 'ticks';
    tickContainer.id = 'tickContainer';
    this.createTicks(tickContainer, 27, 0);

    knod_surround.appendChild(knod);
    knod_surround.appendChild(min);
    knod_surround.appendChild(max);
    knod_surround.appendChild(tickContainer);

    return knod_surround;
}

CustomFields.FieldVelocity.prototype.createTicks = function(tickContainer, numTicks, highlightNumTicks) {
    while (tickContainer.firstChild) {
        tickContainer.removeChild(tickContainer.firstChild);
    }

    var startingTickAngle = -135;
    //create ticks
    for (var i = 0; i < numTicks; i++) {
        var tick = document.createElement("div");

        //highlight only the appropriate ticks using dynamic CSS
        if (i < highlightNumTicks) {
            tick.className = "tick activetick";
        } else {
            tick.className = "tick";
        }

        tickContainer.appendChild(tick);
        tick.style.transform = "rotate(" + startingTickAngle + "deg)";
        startingTickAngle += 10;
    }

    startingTickAngle = -135; //reset

    // return tickContainer;
}

CustomFields.FieldTurtle.prototype.widgetDispose_ = function() {};

CustomFields.FieldVelocity.prototype.showInlineEditor_ = function() {

}