// import Blockly from 'blockly'
// var workspacePlayground = Blockly.inject('blocklyDiv', {
//     media: 'https://unpkg.com/blockly/media/',
//     toolbox: BLOCKLY_TOOLBOX_XML['standard'],
//     zoom: {
//         controls: true
//     }
// });

function handleGenCode() {
    document.getElementById("codeSpaceText").value = "nguyen anh phuong 123";
    Blockly.JavaScript.addReservedWords('code');
    var code = Blockly.JavaScript.workspaceToCode(workspacePlayground);
    document.getElementById("codeSpaceText").value = code;
}

document.getElementById('genCodeCommand').addEventListener('click', handleGenCode);