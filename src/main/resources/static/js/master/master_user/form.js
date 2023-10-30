/*<![CDATA[*/

CKEDITOR.replace('umum_Data');

CKEDITOR.instances['umum_Data'].on('change', function() {$('#dataUmum').val(this.getData());});

function setDataCK(data){
	CKEDITOR.instances['umum_Data'].setData(data);
}

/*]]>*/