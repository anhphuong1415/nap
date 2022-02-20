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

CustomFields.FieldCalculate.prototype.showEditor_ = function() {
    CustomFields.FieldVelocity.superClass_.showEditor_.call(this);

    this.editor_ = this.WidgetCreate_();
}

CustomFields.FieldVelocity.prototype.WidgetCreate_ = function() {
    var widget = this.createWidget_();
    Blockly.WidgetDiv.DIV.appendChild(widget);
}