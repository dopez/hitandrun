var cm;
var RowData;

var getColumnSrcIndexByName = function(grid, colIdx) {
	var i = 0, index = 0, l = cm.length, cmName;
	return cmName = cm[colIdx].name;
};

var getColModel = function(vGridID) {
	cm = $("#" + vGridID).jqGrid('getGridParam', 'colModel');
};

function getRowId(rowIndex)
{
	return $(RowData[rowIndex-1]).attr("id");
}

function getRows(vGridID)
{
	RowData = $('tbody tr', $("#" + vGridID)).filter(':visible:not(.jqgfirstrow)');
}

var getColumnSrcIndex = function(grid, colName) {
	
	var ismultiselect = $(grid).jqGrid('getGridParam', 'multiselect');
	
	var i = 0, index = -1, l = cm.length, cmName;
		
	while (i<l) {         
		cmName = cm[i].name;         
		i++;
		if(cmName === colName) {
			break;
			return index;
		}
		else if (cmName!=='rn' && cmName!=='cb' && cmName!=='subgrid') {             
		    index++;         
		}     
	}
	
	if( ismultiselect )
		index = index + 2;
	else
		index = index + 1;
	
	return index;			
};

function getCellEditable(colIdx)
{
	var isEditable = cm[colIdx].editable;

	if( isEditable )
		return true;

	return false;
}

function Paste(vGridID, vCurRow, vCurCol, vColsLength) {
		var ClipboardText = getClipboard();
		
		if( !confirm("복사된 내용을 해당 리스트에 붙여넣겠습니까?\n\n붙여넣기 시 아래와 같은 경우는 제외됩니다.\n\n-읽기전용 영역인 경우\n-복사된 내용이 리스트의 셀을 벗어나는 경우") )
		{
			return false;
		}
		
		if( ClipboardText == null )
		{
			alert("복사된 내용이 없습니다.");
			return false;
		}
		
		getColModel(vGridID);
		
		if( !getCellEditable(vCurCol) )
		{
			alert("선택된 셀은 편집이 불가능합니다.");
			return false;
		}
		
		var curColName = getColumnSrcIndexByName($("#" + vGridID),vCurCol);
		var curColIdx = getColumnSrcIndex($("#" + vGridID),curColName);
		
		
		var ClipboardText = ClipboardText.replace(/\r\n/g, "SPLIT01");
		var selCol = 0;
		if( ClipboardText.lastIndexOf("SPLIT01") > -1 )
			ClipboardText = ClipboardText.substr(0, ClipboardText.lastIndexOf("SPLIT01"));
		var Rows = ClipboardText.split("SPLIT01");
		getRows(vGridID);
		for ( var iRow = 0; iRow < Rows.length; iRow++) {
			var Cells = Rows[iRow].split('\t');
			for ( var iCol = 0; iCol < Cells.length; iCol++) {
				if( vColsLength > iCol+curColIdx )
				{
					if( getCellEditable(iCol+curColIdx) )
					{
						var colName = getColumnSrcIndexByName($("#" + vGridID),iCol+curColIdx);
						var inRow =  parseInt(vCurRow)+parseInt(iRow);
						var value = $.trim(Cells[iCol]);						
						$("#" + vGridID).setCell( getRowId(inRow) , colName, value);
					}
				}
			}
		}
	}

	function getClipboard() {
		if (window.clipboardData) {
			return (window.clipboardData.getData('Text'));
		}
		return null;
	}
