$(function() {
	alert("DSFF");
	$('#facilityType').change(function() {
		var facilityType = $(this).val();
		showFacilities(facilityType);
	});

	$('.facility .facilityName').click(function() {
		var facility = $(this).parents('tr');
		reserve(facility);
	});
	
	$('.updateBtn').click(function() {
		alert("SS")
		var facilityId = $(this).parents('tr').attr('id');
		location.href = '/facility/update?id=' + facilityId;
	});
	
	$('#reservationForm').submit(function() {
		var totalPrice = parseInt($('#totalPrice').text());
		
		if (totalPrice <= 0) {
			alert('예약할 시설을 선택하세요.');
			return false;
		}
		
		var teamBudget = parseInt($('#teamBudget').val());
		
		if (totalPrice > teamBudget) {
			alert('팀예산을 초과하였습니다.');
			return false;
		}
		
		alert('예약이 완료되었습니다.');
		return true;
	});

	$('#logoutBtn').click(function() {
		location.href = '/member/logout';
	});
	
	function reserve(facility) {
		var reservationDetails = $('.reservationDetails tbody').eq(0);
		var rows = reservationDetails.find('tr');
		
		var facilityId = facility.attr('id');
		
		for (var i = 0; i < rows.length; i++) {
			var rowId = rows.eq(i).attr('id');
			
			if (rowId == facilityId) {
				alert('이미 목록에 있는 시설입니다.');
				return;
			}
		}

		var totalPrice = $('#totalPrice');
		var price = parseInt(facility.find('.price').text());
		
		var facilityName = facility.find('.facilityName').text();
		
		var template = $('#reservationTemplate');
		var row = template.clone();

		row.attr('id', facilityId);
		row.find('.facilityName').text(facilityName);
		row.find('.price').text(price);
		
		var deleteBtn = row.find('button');
		deleteBtn.click(function() {
			var tr = $(this).parents('tr');
			var price = tr.find('.price').text();
			
			totalPrice.text(totalPrice.text() - price);
			tr.remove();
		});
		
		row.removeClass('blind');
		reservationDetails.append(row);

		var tPrice = parseInt(totalPrice.text());
		totalPrice.text(tPrice + price);
	}
	
	function showFacilities(facilityType) {
		$('.facility tbody tr').addClass('blind');
		
		if (facilityType) {
			$('.facility tbody tr.' + facilityType).each(function() {
				$(this).removeClass('blind');
			});
		}
	}
});