# config_zk

  
  <div id="paypal-button-container-P-61V091947G588672JMFFNZ6Q"></div>
<script src="https://www.paypal.com/sdk/js?client-id=AfLC1AdBlnQC4PzdHbS6jlJFJ3OKiUYvOyY_-nqXePPf2UIiQdL7Y3lcaoMUpox122iVm_Q3ZMwYm7pg&vault=true&intent=subscription" data-sdk-integration-source="button-factory"></script>
<script>
  paypal.Buttons({
      style: {
          shape: 'pill',
          color: 'gold',
          layout: 'vertical',
          label: 'subscribe'
      },
      createSubscription: function(data, actions) {
        return actions.subscription.create({
          /* Creates the subscription */
          plan_id: 'P-61V091947G588672JMFFNZ6Q'
        });
      },
      onApprove: function(data, actions) {
        alert(data.subscriptionID); // You can add optional success message for the subscriber here
      }
  }).render('#paypal-button-container-P-61V091947G588672JMFFNZ6Q'); // Renders the PayPal button
</script>
