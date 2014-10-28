<h2>${adForm.title}</h2>
<div>
	${adForm.street} ${adForm.number}<br/>
	${adForm.zipCode} ${adForm.city}
</div>
<div>
	<iframe
		width="400"
		height="400"
		frameborder="0" style="border:0"
		src="https://www.google.com/maps/embed/v1/place?key=AIzaSyAg7gQ_6H5xWUXFVrxyUpulXzs3flqGfcA
			&q=${adForm.street}+${adForm.number},${adForm.zipCode}+${adForm.city},Switzerland">
	</iframe>
</div>
<div>
	Price: ${adForm.price} chf<br/>
	Number of rooms: ${adForm.numberOfRooms}
</div>
<div>
	Description: <br/>
	${adForm.description}
</div>