import React, { Component, useState, setShow } from "react";
import { 
  Button,
  Modal
} from 'react-bootstrap';
import axios from 'axios';


const ModalDelete = (props) => {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);


  return (
    <>
      <Button variant="danger" onClick={handleShow}>
        Stop
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton style={{color:"#212121"}}>
          <Modal.Title>Confirm</Modal.Title>
        </Modal.Header>
        <Modal.Body style={{color:"#212121"}}>Stopping is permanent. Do you want to continue?</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            No
          </Button>
          <Button variant="primary" onClick={ ()=> {
            props.onClick(props.id);
            handleClose();
          }}>
            Yes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};



export default ModalDelete;