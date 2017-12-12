package com.poixson.nettet.transports;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

import com.poixson.nettet.NettetDefines;
import com.poixson.utils.Utils;
import com.poixson.utils.exceptions.RequiredArgumentException;


public class TransportClientUDP extends TransportClient {

	protected final DatagramChannel channel;
	protected final DatagramSocket  socket;

	protected final InetSocketAddress localAddr;
	protected final InetSocketAddress remoteAddr;



	public TransportClientUDP(
			final String remoteAddrStr, final int remotePort)
			throws IOException {
		this(
			null, // localAddrStr
			NettetDefines.RandomPort(),
			remoteAddrStr,
			remotePort
		);
	}
	public TransportClientUDP(
			final String localAddrStr, final int localPort,
			final String remoteAddrStr, final int remotePort)
			throws IOException {
		super();
		if (Utils.isEmpty(remoteAddrStr)) throw RequiredArgumentException.getNew("remoteAddrStr");
		if (localPort  <= 0) throw RequiredArgumentException.getNew("localPort");
		if (remotePort <= 0) throw RequiredArgumentException.getNew("remotePort");
		this.localAddr = (
			Utils.isEmpty(localAddrStr)
			? new InetSocketAddress(localPort)
			: new InetSocketAddress(localAddrStr, localPort)
		);
		this.remoteAddr = new InetSocketAddress(remoteAddrStr, remotePort);
		this.channel = DatagramChannel.open();
		this.socket  = this.channel.socket();
		this.channel.configureBlocking(false);
	}
	// inet
	public TransportClientUDP(
			final InetSocketAddress remoteAddr)
			throws IOException {
		this(
			null,
			remoteAddr
		);
	}
	public TransportClientUDP(
			final InetSocketAddress localAddr,
			final InetSocketAddress remoteAddr)
			throws IOException {
		super();
		if (remoteAddr == null) throw RequiredArgumentException.getNew("remoteAddr");
		this.localAddr  = (
			localAddr == null
			? new InetSocketAddress(NettetDefines.RandomPort())
			: localAddr
		);
		this.remoteAddr = remoteAddr;
		this.channel = DatagramChannel.open();
		this.socket  = this.channel.socket();
		this.channel.configureBlocking(false);
	}



	@Override
	public void connect() throws IOException {
		throw new UnsupportedOperationException();
	}



	@Override
	public void close() throws IOException {
		this.channel.close();
	}



	@Override
	public boolean isClosed() {
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean isConnected() {
		throw new UnsupportedOperationException();
	}



}
