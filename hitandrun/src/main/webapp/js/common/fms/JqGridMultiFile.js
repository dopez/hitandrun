/**
 * Convert a single file-input element into a 'multiple' input list
 * Usage:
 *
 *   1. Create a file input element (no name)
 *      eg. <input type="file" id="first_file_element">
 *
 *   2. Create a DIV for the output to be written to
 *      eg. <div id="files_list"></div>
 *
 *   3. Instantiate a MultiSelector object, passing in the DIV and an (optional) maximum number of files
 *      eg. var multi_selector = new MultiSelector( document.getElementById( 'files_list' ), 3 );
 *
 *   4. Add the first element
 *      eg. multi_selector.addElement( document.getElementById( 'first_file_element' ) );
 */

 function setFileChk(target)
 {
 	var existExt = ".exe,.asp,.cmd,.class,.html,.js,.jsp,.php,.sh,.csh";
 	var existExtArray = existExt.split(",");
 	
 	var Temp_file_name = target;
 	if(Temp_file_name != "")
 	{
 		Temp_strExt_num = Temp_file_name.slice(Temp_file_name.indexOf(".")).toLowerCase();
 		for(var tmpcnt = 0; tmpcnt < existExtArray.length; tmpcnt++)
 		{
 			if(Temp_strExt_num == existExtArray[tmpcnt])
 			{
 				return false;
 			}
 		}
 	}
 	return true;
 	
 }
 

function MultiSelector( list_target, max, prefix, addfilename ){

	// Where to write the list
	this.list_target = list_target;
	// How many elements?
	this.count = 0;
	// How many elements?
	this.id = 0;
	// Is there a maximum?
	if( max ){
		this.max = max;
	} else {
		this.max = -1;
	};
	this.prefix = prefix;
	this.addfilename = addfilename;
	
	/**
	 * Add a new file input element
	 */
	this.addElement = function( element ){

		// Make sure it's a file input element
		if( element.tagName == 'INPUT' && element.type == 'file' ){
			
			// Element name -- what number am I?
			element.name = prefix +"_" + this.id++;
			element.id   = element.name;
			
			// Add reference to this object
			element.multi_selector = this;

			// What to do when a file is selected
			element.onchange = function(){
				
				// New file input
				var new_element = document.createElement( 'input' );
				new_element.type = 'file';
				
				// Hide this: we can't use display:none because Safari doesn't like it
				this.style.position = 'absolute';
				this.style.left = '-1000px';
				this.style.top = '-1000px';
				this.style.display = 'none';
				this.style.visibility = 'hidden';
				this.style.width = '0';
				this.style.height = '0';
				this.style.overflow = 'hidden';
				
				//new_element.style.display = 'none';
				
				// Add new element
				this.parentNode.insertBefore( new_element, this );

				// Apply 'update' to element
				this.multi_selector.addElement( new_element );

				// Update list
				this.multi_selector.addListRow( this );				
				

				new_element.onkeypress = function(){
					return false;
				};

			};
			// Most recent element
			this.current_element = element;
			
		} else {
			// This can only be applied to file input elements!
			alert( 'Error: not a file input element' );
		};

	};

	/**
	 * 첨부파일리스트에 추가한다. 
	 */
	var filecnt = 0;
	this.addListRow = function( element ){
		
		if (!setFileChk(element.value)){
			alert("해당 확장자 파일은 업로드 할 수 없습니다.");
			return false;
		}
		var rows = $("#" + this.addfilename ).jqGrid('getRowData');
		
		if( this.max <= rows.length ) {
			alert("첨부파일 갯수를 초과했습니다.");
			return false;				
		}

		
		var mydata = [
		                {orignlFileNm:"",size:"", status:"전송준비", id:""}
		             ];
		
		mydata[0].orignlFileNm  = element.value; 
		mydata[0].id   = element.name;
		mydata[0].size = getFileSize( element.value );
		
		$("#" + this.addfilename ).jqGrid('addRowData', filecnt++, mydata[0]);
		
		
	};

};

function getFileSize( path  ) 
{     
    var image = new Image();	
    image.src = path;
    return image.fileSize;
    	
}
